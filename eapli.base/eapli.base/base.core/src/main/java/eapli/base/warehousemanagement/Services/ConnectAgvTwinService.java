package eapli.base.warehousemanagement.Services;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

@ApplicationService
public class ConnectAgvTwinService {

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    static final String keyStorePassProperties = "forgotten";

    static InetAddress server_ip;
    static SSLSocket socket;

    public void connectTwin(int option, Long orderId, String agvId) throws IOException {

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
            socket = (SSLSocket) socketFactory.createSocket(server_ip, 9990);
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

                byte[] clientOptionChoosen = {(byte) 0, (byte) 10, (byte) 0, (byte) 0};
                dataOutputStream.write(clientOptionChoosen);
                dataOutputStream.flush();

                //Waits for the server response
                byte[] serverConfirmation = dataInputStream.readNBytes(4);

                if (serverConfirmation[1] == 2) {

                    System.out.println("Received confirmation message from the server (id -> 2)");

                    int code = Integer.parseInt(String.valueOf(agvId.charAt(agvId.length() - 1)));

                    dataOutputStream.write(new byte[]{(byte) 0, (byte) 0, Byte.parseByte(String.valueOf(orderId)), (byte) code});
                    dataOutputStream.flush();

                    serverConfirmation = dataInputStream.readNBytes(4);
                    if (serverConfirmation[1] == 2) {
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
                    }else{
                        throw new IllegalArgumentException("Server did not find that option!");
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