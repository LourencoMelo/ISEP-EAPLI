package eapli.base.app.agvManager;

import eapli.base.SPOMSPProtocol.Constants;
import eapli.base.SPOMSPProtocol.MessageParser;
import eapli.base.SPOMSPProtocol.SPOMSPRequest;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.warehousemanagement.Services.ConnectAgvTwinFIFOService;
import eapli.base.warehousemanagement.Services.ConnectAgvTwinService;
import eapli.base.warehousemanagement.domain.agv.AGV;
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

        System.out.println("Server is open and running stable!");
        System.out.println("Waiting for clients...\n");

        while (true) {
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
        this.s = cli_s;
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

        DataInputStream sIn = null;
        DataOutputStream sOut = null;

        InetAddress clientIP;

        clientIP = s.getInetAddress();
        System.out.println("New connection from " + clientIP.getHostAddress() + ",port number " + s.getPort() + ".");

        try {
            sIn = new DataInputStream(s.getInputStream());
            sOut = new DataOutputStream(s.getOutputStream());

            //Reads first client's message
            byte[] clientMessage = sIn.readNBytes(4);

            //Parses the message from the client to return the correct answer
            SPOMSPRequest spomspRequest = MessageParser.parse(clientMessage);

            //If the clients message code is 0 represents test request
            if (clientMessage[Constants.CODE_OFFSET] == Constants.COMMTEST) {

                System.out.println("[INFO] Communication Test Request received by port : " + s.getPort() + "\n\n");

                //Saves the answer to be sent
                byte[] answer = spomspRequest.execute();

                //Checks if the answer is valid. If not, the protocol doesn't support
                if (answer == null) {
                    throw new IllegalArgumentException("[ERROR] Protocol Error \n\n");
                }

//                System.out.println("[INFO] Sent acknowledgment message to client\n\n");

                //Writes the confirmation message to the client. 1st message trade
                sOut.write(answer);
                //Forces the data out of the socket
                sOut.flush();

                System.out.println("[INFO - " + s.getPort() + "] Sent acknowledgment message to client\n\n");


                //Reads the client's option. 2nd message trade
                byte[] clientsOption = sIn.readNBytes(4);
                int option = clientsOption[1];

                System.out.println("[INFO - " + s.getPort() + "] Client option is : " + option + "\n\n");

                //Sends confirmation to the client. 2nd message trade
                sOut.write(answer);
                sOut.flush(); //Forces the data out of the socket

                Thread.sleep(3000);

                if (option >= 3) {
                    switch (option) {
                        //Case where the backoffice communicates with the server to assign an order manually
                        case 3:
                            ObjectInputStream sInputObject = new ObjectInputStream(s.getInputStream());
                            SystemUser systemUser = (SystemUser) sInputObject.readObject();
                            System.out.println("[INFO] User logged in: " + systemUser.username() + "\n\n");

                            byte[] message = sIn.readNBytes(4);
                            String id = String.valueOf(message[3]);
                            System.out.println("[INFO] Order received :" + id + "\n\n");

                            byte[] clientMessage2 = sIn.readNBytes(4); //Reads all bytes from client's message

                            //Checks if the client requests to end the conection
                            if (clientMessage2[Constants.CODE_OFFSET] == Constants.DISCONN) {
                                System.out.println("[INFO] Request to end the communication received.\n\n");

                                sOut.write(answer);
                                sOut.flush(); //Forces the data out of the socket
                            }
                            forceOrder(id);

                            break;
                        //Case where the agvs communicate with the server telling they are ready
                        case 4:

                            System.out.println("[INFO] AGV ID : agv-" + clientsOption[3] + " connected!\n\n");
                            changeToReady("agv-" + clientsOption[3]);

                            byte[] clientMessage3 = sIn.readNBytes(4); //Reads all bytes from client's message

                            //Checks if the client requests to end the conection
                            if (clientMessage3[Constants.CODE_OFFSET] == Constants.DISCONN) {
                                System.out.println("[INFO] Request to end the communication received\n\n");

                                sOut.write(answer);
                                sOut.flush(); //Forces the data out of the socket
                            }

                            break;
                        //Case where the backoffice communicates with the server to enable the automatic assignemt of tasks
                        case 5:
                            ObjectInputStream sInputObject2 = new ObjectInputStream(s.getInputStream());
                            SystemUser systemUser2 = (SystemUser) sInputObject2.readObject();
                            System.out.println("[INFO] User logged in: " + systemUser2.username() + "\n\n");

                            System.out.println("[INFO] Starting automatic assignment!\n\n");
                            automaticTaskAssignment();
                            System.out.println("[INFO] Automatic assignment done!\n\n");

                            byte[] clientMessage4 = sIn.readNBytes(4); //Reads all bytes from client's message

                            //Checks if the client requests to end the conection
                            if (clientMessage4[Constants.CODE_OFFSET] == Constants.DISCONN) {
                                System.out.println("[INFO] Request to end the communication received\n\n");

                                sOut.write(answer);
                                sOut.flush(); //Forces the data out of the socket
                            }

                            break;
                        default:
                            System.out.println("[INFO] Unexistent option!\n\n");
                    }
                }

            }

            System.out.println("[INFO] Client " + clientIP.getHostAddress() + ",port number: " + s.getPort() + " disconnected!\n\n");
            s.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.s.close();
                System.out.println("[INFO] Socket closed!\n\n");
            } catch (IOException e) {
                System.out.println("[Error] Could not close the socket!\n\n");
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

    private void automaticTaskAssignment() throws IOException {

        ctx.beginTransaction();
        //Orders queue
        //Auxiliar list to order the orders
        List<Order> auxList = new ArrayList<>();
        List<AGV> auxAGV = new ArrayList<>();

        //Adds all the orders that are waiting to be prepared to the aux list
        orderRepository.ordersToBePrepared().forEach(auxList::add);

        ctx.commit();

        ConnectAgvTwinFIFOService connectAgvTwinFIFOService = new ConnectAgvTwinFIFOService();

        LinkedList<Order> orders_queue = new LinkedList<>(auxList);

        List<Order> auxOrdersList = new ArrayList<>();

        int i;

        for (i = 0; i < orders_queue.size(); i++) {

            ctx.beginTransaction();

            Order order = orders_queue.get(i);

            System.out.println("=====================" + order.identity() + "=====================");

            //List of capableAgvs
            List<AGV> capableAgvs = agvRepository.findAvailableAGVS(order.calculateTotalOderWeight(), order.calculateTotalOrderVolume());

            if (!capableAgvs.isEmpty()) {

                //Gets the first capable agv and assigns order to it
                AGV capableAgv = capableAgvs.get(0);

                auxAGV.add(capableAgv);

                capableAgv.assignOrder(order);
                order.isInPreparation();
                order.setResponsableAGV(capableAgv);
                orderRepository.save(order);

                ctx.commit();

                connectAgvTwinFIFOService.connectTwinFIFO(12,order.getPk(),capableAgv.identity().getAgvId());

                orders_queue.remove(order);
                auxOrdersList.add(order);
                i--;

            } else {
                System.out.println("[INFO] No capable agvs were ready to assign the order with id : " + order.identity());
                System.out.println("[INFO] Please try later");
                ctx.commit();
            }
        }
        ctx.beginTransaction();

        for (Order order : auxOrdersList) {
            order.isDispatched();
        }

        for (AGV agv: auxAGV) {
            agv.activateAGV();
            agvRepository.save(agv);
        }
        ctx.commit();
    }

    private boolean forceOrder(String id) throws IOException {
        ConnectAgvTwinService connectAgvTwinService = new ConnectAgvTwinService();
        ctx.beginTransaction();
        if (orderRepository.findOrderById(Long.valueOf(id)).isPresent()) {
            Order orderWanted = orderRepository.findOrderById(Long.valueOf(id)).get();
            List<AGV> capableAgvs = agvRepository.findAvailableAGVS(orderWanted.calculateTotalOderWeight(), orderWanted.calculateTotalOrderVolume());
            if (capableAgvs.isEmpty()) {
                System.out.println("[INFO] No capable agvs were ready to assign the order with id : " + orderWanted.identity());
                System.out.println("[INFO] Please try later");
                return false;
            }
            AGV capableOne = capableAgvs.get(0);
            capableOne.assignOrder(orderWanted);
            orderWanted.isInPreparation();
            orderWanted.setResponsableAGV(capableOne);
            orderRepository.save(orderWanted);
            agvRepository.save(capableOne);

            ctx.commit();

            connectAgvTwinService.connectTwin(10,orderWanted.getPk(),capableOne.identity().getAgvId());

            ctx.beginTransaction();

            orderWanted.isDispatched();
            capableOne.activateAGV();

            orderRepository.save(orderWanted);
            agvRepository.save(capableOne);

            ctx.commit();
            return true;
        }
        return false;
    }
}