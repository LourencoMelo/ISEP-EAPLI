package eapli.base.ordermanagement.Services;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@ApplicationService
public class AssignOrderService {

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    //    static final int serverPortProperties = app.getServerPortKey();
//    static final String keyStorePassProperties = app.getKeysStorePassAgvManagerKey();
    static final String keyStorePassProperties = "forgotten";

    static InetAddress server_ip;
    static SSLSocket socket;

    public void assignOrderToAGV(SystemUser user, int option, Long orderId) throws IOException {

        try {
            server_ip = InetAddress.getByName(serverIPProperties);
        } catch (UnknownHostException exception) {
            throw new IllegalArgumentException("Server error");
        }

        String certificate = "client" + (option - 2) + "AGV";

        //Certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", certificate + ".jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "forgotten");

        System.setProperty("javax.net.ssl.keyStore", certificate + ".jks");
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            socket = (SSLSocket) socketFactory.createSocket(server_ip, 9999);
        } catch (IOException exception) {
            throw new IllegalArgumentException("Server problems!");
        }

        try {
            socket.startHandshake();

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            byte[] clientMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};

            dataOutputStream.write(clientMessage);

            dataOutputStream.flush();

            byte[] serverMessage = dataInputStream.readNBytes(4);
            if (serverMessage[1] == 2) {

                System.out.println("Received confirmation message from the server");

                int choose = Console.readInteger("Which request do you want to do? Please watch the protocol message available codes.");

                //Sends to the server the option choosen on the 2 offset from the message
                byte[] clientOptionChoosen = {(byte) 0, (byte) choose, (byte) 0, (byte) 0};
                dataOutputStream.write(clientOptionChoosen);
                dataOutputStream.flush();

                //Waits for the server response
                byte[] serverConfirmation = dataInputStream.readNBytes(4);

                //Verifies if the server response is a confirmation code
                if (serverConfirmation[1] == 2) {

                    System.out.println("Received confirmation message from the server (id -> 2)");

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                    //Sends the current user for identification purpose and then flushes the channel
                    objectOutputStream.writeObject(user);
                    objectOutputStream.flush();

                    System.out.println(orderId);

                    //Sends the wanted order to the server application
//                    objectOutputStream.writeObject(orderId);
//                    objectOutputStream.flush();
                    dataOutputStream.write(new byte[]{(byte) 0, (byte) 0, (byte) 0,Byte.parseByte(String.valueOf(orderId))});
                    dataOutputStream.flush();

                    //Communicates with server side requesting to end the connection
                    byte[] clientMessageEnding = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    dataOutputStream.write(clientMessageEnding);
                    dataOutputStream.flush();

                    //Reads server response. Verifies if it contains confirmation code
                    byte[] serverMessageEnding = dataInputStream.readNBytes(4);
                    if (serverMessageEnding[1] == 2) {
                        System.out.println("Server approved the ending of the communication.");
                        socket.close();
                        System.out.println("Disconnected!");
                    } else {
                        socket.close();
                    }
                } else {
                    throw new IllegalArgumentException("Server did not find that option!");
                }
            } else {
                throw new IllegalArgumentException("Error with server connection!");
            }
        } catch (IOException exception) {
            throw new IOException("Error with server communication!");
        } finally {
            socket.close(); //In case of server application doesn't response confirmation code
        }

    }
}
