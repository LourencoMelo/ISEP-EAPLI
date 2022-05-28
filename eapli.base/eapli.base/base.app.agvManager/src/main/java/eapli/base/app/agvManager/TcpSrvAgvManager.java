package eapli.base.app.agvManager;

import eapli.base.SPOMSPProtocol.Constants;
import eapli.base.SPOMSPProtocol.MessageParser;
import eapli.base.SPOMSPProtocol.SPOMSPRequest;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.Status;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import eapli.base.ordermanagement.domain.Order;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class TcpSrvAgvManager {


    static int SERVER_PORT = 9999;

    static final String TRUSTED_STORE = "serverAgvManager.jks";
    static final String KEYSTORE_PASS = "forgotten";

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {

        System.out.println("Configuring the server");
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(), new PlainTextEncoder());

        SSLServerSocket sock = null;
        Socket cliSock;

        //Trust these certificates provided by authorizes clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        //Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            sock = (SSLServerSocket) sslServerSocketFactory.createServerSocket(SERVER_PORT);
            sock.setNeedClientAuth(true);
        } catch (IOException exception) {
            System.out.println("Server failed to open local port " + SERVER_PORT);
            System.exit(1);
        }

        while (true) {
            System.out.println("Server is open and running stable!");
            cliSock = sock.accept();
            new Thread(new TcpSrvAgvManagerThread(cliSock)).start(); //Creates new thread to handle the client and still be open for more requests
        }
    }


}

class TcpSrvAgvManagerThread implements Runnable {

    private final Socket s;

    private final TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv(ctx);

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders(ctx);

    public TcpSrvAgvManagerThread(Socket cli_s) {
        s = cli_s;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        InetAddress clientIP;

        clientIP = s.getInetAddress();
        System.out.println("New connection from " + clientIP.getHostAddress() + ",port number " + s.getPort() + ".");

        try {
            DataInputStream sIn = new DataInputStream(s.getInputStream());
            DataOutputStream sOut = new DataOutputStream(s.getOutputStream());

            //Reads all bytes from client's message
            byte[] clientMessage = sIn.readNBytes(4);

            //Parses the message from the client to return the correct answer
            SPOMSPRequest spomspRequest = MessageParser.parse(clientMessage);

            //If the clients message code is 0 represents test request
            if (clientMessage[Constants.CODE_OFFSET] == Constants.COMMTEST) {

                System.out.println("Communication Test Request received by port : " + s.getPort());

                //Saves the answer to be sent
                byte[] answer = spomspRequest.execute();

                //Checks if the answer is valid. If not, the protocol doesn't support
                if (answer == null) {
                    throw new IllegalArgumentException("Protocol Error");
                }

                System.out.println("Sent acknowledgment message to client.");

                System.out.println("Answer : ");
                System.out.println("version : " + answer[0]);
                System.out.println("Code : " + answer[1]);

                sOut.write(answer);
                //Forces the data out of the socket
                sOut.flush();


                byte[] clientsOption = sIn.readNBytes(4);
                int option = clientsOption[1];

                System.out.println("Client option is : " + option);

                sOut.write(answer);
                sOut.flush(); //Forces the data out of the socket

                Thread.sleep(3000);

                boolean isAGVClient = false;


                if (option >= 3) {
                    switch (option) {
                        //Case where the backoffice communicates with the server to assign an order manually
                        case 3:
                            ObjectInputStream sInputObject = new ObjectInputStream(s.getInputStream());
                            SystemUser systemUser = (SystemUser) sInputObject.readObject();
                            System.out.println("User logged in: " + systemUser.username());

//                            Order order = (Order) sInputObject.readObject();
                            byte[] message = sIn.readNBytes(4);
                            String id = String.valueOf(message[3]);
                            System.out.println("Order received :" + id);

                            forceOrder(id);
                            
                            byte[] clientMessage2 = sIn.readNBytes(4); //Reads all bytes from client's message

                            //Checks if the client requests to end the conection
                            if (clientMessage2[Constants.CODE_OFFSET] == Constants.DISCONN) {
                                System.out.println("Request to end the communication received.");

                                sOut.write(answer);
                                sOut.flush(); //Forces the data out of the socket
                            }
                            break;
                        //Case where the agvs communicate with the server telling they are ready
                        case 4:
                            System.out.println("Arrived");
                            System.out.println("AVG ID : agv-" + clientsOption[3]);
                            changeToReady("agv-" + clientsOption[3]);
                            break;
                        //Case where the backoffice communicates with the server to enable the automatic assignemt of tasks
                        case 5:
                            System.out.println("Starting automatic assignment!");
                            automaticTaskAssignment();
                            System.out.println("Automatic assignment done!");
                            break;
                        default:
                            System.out.println("...");
                    }
                }

            }

            System.out.println("Client " + clientIP.getHostAddress() + ",port number: " + s.getPort() + " disconnected");
            s.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.s.close();
                System.out.println("Socket closed!");
            } catch (IOException e) {
                System.out.println("Error : Could not close the socket");
            }
        }
    }


    private void changeToReady(String agvId) {
        ctx.beginTransaction();
        AGV agv = agvRepository.findAGVById(agvId).get();

        agvRepository.findAGVById(agvId).get().activateAGV();

        agvRepository.save(agv);
        ctx.commit();

        System.out.println("AVG set to ready");
    }

    private void automaticTaskAssignment() {
        //Orders queue
        //Auxiliar list to order the orders
        List<Order> auxList = new ArrayList<>();
        //Adds all the orders that are waiting to be prepared to the aux list
        orderRepository.ordersToBePrepared().forEach(auxList::add);

        auxList.sort(Comparator.comparing(Order::getRegistDate));

        Queue<Order> orders_queue = new LinkedList<>(auxList);

        for (Order order : orders_queue) {

            boolean wasAssigned = false;

            ctx.beginTransaction();

            //List of capableAgvs
            List<AGV> capableAgvs = agvRepository.findAvailableAGVS(order.calculateTotalOderWeight(), order.calculateTotalOrderVolume());

            for (AGV capableAgv : capableAgvs) {
                capableAgv.assignOrder(order);
                agvRepository.save(capableAgv);
                orders_queue.remove(order);
                System.out.println("[INFO] Order with id -> " + order.identity() + "was assigned to agv with id " + capableAgv.identity().toString());
                wasAssigned = true;
                break;
            }

            if (!wasAssigned) {
                System.out.println("[INFO] No capable agvs were ready to assign the order with id : " + order.identity());
                System.out.println("[INFO] Please try later");
            }
        }

        ctx.commit();
    }

    private boolean forceOrder(String id) {
        ctx.beginTransaction();
        if (orderRepository.findOrderById(Long.valueOf(id)).isPresent()) {
            Order orderWanted = orderRepository.findOrderById(Long.valueOf(id)).get();
            List<AGV> capableAgvs = agvRepository.findAvailableAGVS(orderWanted.calculateTotalOderWeight(), orderWanted.calculateTotalOrderVolume());
            if(capableAgvs.isEmpty()){
                System.out.println("[INFO] No capable agvs were ready to assign the order with id : " + orderWanted.identity());
                System.out.println("[INFO] Please try later");
                return false;
            }
            AGV capableOne = capableAgvs.get(0);
            capableOne.assignOrder(orderWanted);
            agvRepository.save(capableOne);
            System.out.println("[INFO] Order with id -> " + orderWanted.identity() + "was assigned to agv with id " + capableOne.identity().toString());
            ctx.commit();
            return true;
        }
        return false;
    }
}