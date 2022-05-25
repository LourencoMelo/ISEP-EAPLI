package eapli;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class TcpClientAgvDigitalTwin {

    public static void main(String[] args) {
        List<String> ids = Arrays.asList("agv-1", "agv-2");
        for (String id : ids) {
            new Thread(new AgvDigitalTwinThread(id, ids.indexOf(id))).start();
        }

    }
}

class AgvDigitalTwinThread implements Runnable {

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    //    static final int serverPortProperties = app.getServerPortKey();
//    static final String keyStorePassProperties = app.getKeysStorePassAgvManagerKey();
    static final String keyStorePassProperties = "forgotten";

    static InetAddress server_ip;
    static SSLSocket socket;

    String id;
    int idPos;

    public AgvDigitalTwinThread(String id, int idPos) {
        this.id = id;
        this.idPos = idPos;
    }

    @Override
    public void run() {
        try {
            server_ip = InetAddress.getByName(serverIPProperties);
        } catch (UnknownHostException exception) {
            throw new IllegalArgumentException("Server error");
        }

        String certificate = "client " + (idPos + 3) + " AGV";

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

            byte[] clientMessage = {(byte) 0, (byte) 4, (byte) 0, Byte.parseByte(id)};

            dataOutputStream.write(clientMessage);

            dataOutputStream.flush();





        } catch (IOException exception) {
            try {
                throw new IOException("Error with server communication!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                socket.close(); //In case of server application doesn't response confirmation code
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
