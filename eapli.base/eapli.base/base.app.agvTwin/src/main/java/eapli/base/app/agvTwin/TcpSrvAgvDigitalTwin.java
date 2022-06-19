package eapli.base.app.agvTwin;

import eapli.base.SPOMSPProtocol.Constants;
import eapli.base.SPOMSPProtocol.MessageParser;
import eapli.base.SPOMSPProtocol.SPOMSPRequest;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TcpSrvAgvDigitalTwin {

    static int SERVER_PORT = 9990;

    static final String TRUSTED_STORE = "serverAgvManager.jks";

    static final String KEYSTORE_PASS = "forgotten";

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws IOException {

        System.out.println("Configuring the server");

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
            new Thread(new TcpSrvAgvDigitalTwinThread(cliSock)).start(); //Creates new thread to handle the client and still be open for more requests
        }

    }

}

class TcpSrvAgvDigitalTwinThread implements Runnable{

    private final Socket socket;

    public TcpSrvAgvDigitalTwinThread(Socket socket) {
        this.socket = socket;
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

        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;

        InetAddress clientIP;

        clientIP = socket.getInetAddress();

        System.out.println("New connection from " + clientIP.getHostAddress() + ",port number " + socket.getPort() + ".");

        try{
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            //Reads first client's message
            byte[] clientMessage = dataInputStream.readNBytes(4);

            //Parses the message from the client to return the correct answer
            SPOMSPRequest spomspRequest = MessageParser.parse(clientMessage);

            //If the clients message code is 0 represents test request
            if (clientMessage[Constants.CODE_OFFSET] == Constants.COMMTEST) {

                System.out.println("[INFO] Communication Test Request received by port : " + socket.getPort() + "\n\n");

                //Saves the answer to be sent
                byte[] answer = spomspRequest.execute();

                //Checks if the answer is valid. If not, the protocol doesn't support
                if (answer == null) {
                    throw new IllegalArgumentException("[ERROR] Protocol Error \n\n");
                }

                //Writes the confirmation message to the client. 1st message trade
                dataOutputStream.write(answer);
                //Forces the data out of the socket
                dataOutputStream.flush();

                System.out.println("[INFO - " + socket.getPort() + "] Sent acknowledgment message to client\n\n");

                //Reads the client's option. 2nd message trade
                byte[] secondMessage = dataInputStream.readNBytes(4);
                int code = secondMessage[1];

                //Sends confirmation to the client. 2nd message trade
                dataOutputStream.write(answer);
                dataOutputStream.flush(); //Forces the data out of the socket

                Thread.sleep(3000);

                //Case to simulate order time
                if(code == 10){

                    byte[] orderInfo = dataInputStream.readNBytes(4);

                    String orderId = String.valueOf(orderInfo[2]);
                    System.out.println(orderId);
                    String agvId = "agv-" + String.valueOf(orderInfo[3]);

                    System.out.println("[INFO] [" + agvId + "] The order with id " + orderId + "was assigned to me!");
                    System.out.println("[INFO] [" + agvId + "] Preparing the order...");

                    //Simulates tasks time
                    Thread.sleep(30000);

                    System.out.println("[INFO] [" + agvId + "] Finished order with id " + orderId + "!");

                    //Sends to client confirmation that the order was prepared
                    dataOutputStream.write(answer);
                    dataOutputStream.flush();

                    byte[] thirdMessage = dataInputStream.readNBytes(4);
                    if(thirdMessage[1] == 1){
                        System.out.println("[INFO] Request to end the communication received.\n\n");

                        dataOutputStream.write(answer);
                        dataOutputStream.flush(); //Forces the data out of the socket
                        socket.close();
                    }
                    socket.close();
                }
                if(code == 12){
                    byte[] orderInfo = dataInputStream.readNBytes(4);

                    String orderId = String.valueOf(orderInfo[2]);
                    System.out.println(orderId);
                    String agvId = "agv-" + String.valueOf(orderInfo[3]);

                    System.out.println("[INFO] [" + agvId + "] The order with id " + orderId + "was assigned to me!");
                    System.out.println("[INFO] [" + agvId + "] Preparing the order...");
                    System.out.println("[INFO] [" + agvId + "] Preparing the order...");
                    System.out.println("[INFO] [" + agvId + "] Finished order with id " + orderId + "!");

                    //Sends to client confirmation that the order was prepared
                    dataOutputStream.write(answer);
                    dataOutputStream.flush();

                    byte[] thirdMessage = dataInputStream.readNBytes(4);
                    if(thirdMessage[1] == 1){
                        System.out.println("[INFO] Request to end the communication received.\n\n");

                        dataOutputStream.write(answer);
                        dataOutputStream.flush(); //Forces the data out of the socket
                        socket.close();
                    }
                    socket.close();
                }
            }
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}