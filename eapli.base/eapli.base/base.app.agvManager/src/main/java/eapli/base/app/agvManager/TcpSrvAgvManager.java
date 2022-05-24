package eapli.base.app.agvManager;

import eapli.base.SPOMSPProtocol.MessageParser;
import eapli.base.SPOMSPProtocol.SPOMSPRequest;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import eapli.base.ordermanagement.domain.Order;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpSrvAgvManager {

    /**
     * List of all available agvs
     */
    static List<AGV> available_agvs = new ArrayList<>();

    static int SERVER_PORT = 9999;

    static final String TRUSTED_STORE = "serverAgvManager.jks";
    static final String KEYSTORE_PASS = "forgotten";

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {

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
        System.out.println("New client connection from " + clientIP.getHostAddress() + ",port number " + s.getPort() + ".");

        try {
            DataInputStream sIn = new DataInputStream(s.getInputStream());
            DataOutputStream sOut = new DataOutputStream(s.getOutputStream());



            byte[] clientMessage = sIn.readNBytes(4); //Reads all bytes from client's message

            SPOMSPRequest spomspRequest = MessageParser.parse(clientMessage);

            if (clientMessage[1] == 0) {
                //System.out.println("Test code(0) received from client.");

                byte[] answer =spomspRequest.execute();
                if (answer == null){
                    throw new IllegalArgumentException("Protocol Error");

                }
//                byte[] answer = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};   //Answer with acknowledgment code
                System.out.println("Sending acknowledgment message to client.");

                sOut.write(answer);
                sOut.flush(); //Forces the data out of the socket

                byte[] clientsOption = sIn.readNBytes(4);
                int option = clientsOption[1];

                System.out.println("Client option is : " + option);

                sOut.write(answer);
                sOut.flush(); //Forces the data out of the socket

                ObjectInputStream sInputObject = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream sOutputObject = new ObjectOutputStream(s.getOutputStream());

                if (option >= 3){
                    SystemUser systemUser = (SystemUser) sInputObject.readObject();
                    System.out.println("User logged in: " + systemUser.username());


//                    System.out.println(sInputObject.readObject());

                    Order order = (Order) sInputObject.readObject();
                    System.out.println("Order received :" + order.toString());

                    byte[] clientMessage2 = sIn.readNBytes(4); //Reads all bytes from client's message
                    if (clientMessage2[1] == 1){
                        System.out.println("Request to end the communication received.");

                        sOut.write(answer);
                        sOut.flush(); //Forces the data out of the socket
                    }
//
//                    switch (option){
//                        case 3:
//                            break;
//                        case 4 :
//                            //does something
//                            break;
//                        case 5 :
//                            //does something
//                            break;
//                        default :
//                            System.out.println("...");
//                    }
                }

            }

            System.out.println("Client " + clientIP.getHostAddress() + ",port number: " + s.getPort() + " disconnected");
            s.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                this.s.close();
                System.out.println("Socket closed!");
            }catch (IOException e){
                System.out.println("Error : Could not close the socket");
            }
        }
    }
}