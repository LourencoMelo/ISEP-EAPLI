package main;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpSrvAgvManager {

    static final int SERVER_PORT = 9999;
    static final String TRUSTED_STORE = "server_J.jks";
    static final String KEYSTORE_PASS = "forgotten";

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

            byte[] clientMessage = sIn.readAllBytes(); //Reads all bytes from client's message

            if (clientMessage[1] == 0) {
                System.out.println("Test code(0) received from client.");

                byte[] answer = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};   //Answer with acknowledgment code
                System.out.println("Sending acknowledgment message to client(2).");

                sOut.write(answer);
                sOut.flush(); //Forces the data out of the socket

                byte[] clientsOption = sIn.readAllBytes();
                int option = clientsOption[1];

                sOut.write(answer);
                sOut.flush(); //Forces the data out of the socket

                ObjectInputStream sInputObject = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream sOutputObject = new ObjectOutputStream(s.getOutputStream());

                if (option >= 3){
                    SystemUser systemUser = (SystemUser) sInputObject.readObject();
                    System.out.println("User logged in: " + systemUser.username());

                    switch (option){
                        case 3:
                            //does something
                            break;
                        case 4 :
                            //does something
                            break;
                        case 5 :
                            //does something
                            break;
                        default :
                            System.out.println("...");
                    }
                }

            }

            System.out.println("Client " + clientIP.getHostAddress() + ",port number: " + s.getPort() + "disconnected");
            s.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}