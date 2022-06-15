package eapli.base.app.agvTwin;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.SPOMSPProtocol.Constants;

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
        List<String> ids = List.of("agv-1","agv-2","agv-3","agv-4");
//
        for (String id : ids) {
            new Thread(new AgvDigitalTwinThread(id, ids.indexOf(id))).start(); //Creates a thread for each agv available simulating agv digital twin
            System.out.println("Thread created : " + id);
        }

    }
}

class AgvDigitalTwinThread implements Runnable {

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
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

        int sleep = idPos * 10000;

        System.out.println("[INFO] Agv with id " + id + " started!");

        try {
            server_ip = InetAddress.getByName(serverIPProperties);
            System.out.println("Server ip = " + server_ip);
        } catch (UnknownHostException exception) {
            System.out.println(exception.getMessage());

        }

        String certificate = "client" + (idPos + 3) + "AGV";

        System.out.println("Cretificate prefix : " + certificate);

        //Certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", certificate + ".jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "forgotten");

        System.setProperty("javax.net.ssl.keyStore", certificate + ".jks");
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            Thread.sleep(sleep);
            socket = (SSLSocket) socketFactory.createSocket(server_ip, 9999);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {

            socket.startHandshake();

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            System.out.println("[INFO] [" + id + "] Asked the server for communication test!\n\n");

            //Sends communication test to the server
            byte[] message = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};

            dataOutputStream.write(message);

            dataOutputStream.flush();

            //Reads answer from the server
            byte[] anwser = dataInputStream.readNBytes(4);

            //Checks if the server answer is confirmation
            if (anwser[1] != 2){
                throw new IOException("[ERROR] [" + id + "] Server communication error!\n\n");
            }

            System.out.println("[INFO] [" + id + "] Received confirmation message from Server!\n\n");

            Thread.sleep(3000);

            int code = Integer.parseInt(String.valueOf(id.charAt(id.length()-1)));
            //Sends message with code "4" and id that warns the agv manager about his status being ready
            message = new byte[]{(byte) 0, (byte) 4, (byte) 0, (byte) code};

            System.out.println("[INFO] [" + id + "] Sending request to change my status to Ready!\n\n");

            dataOutputStream.write(message);
            dataOutputStream.flush();

            anwser = dataInputStream.readNBytes(4);

            //Checks if the server answer is confirmation
            if (anwser[1] != 2){
                throw new IOException("[ERROR] [" + id + "] Server communication error!");
            }

            dataOutputStream.write(new byte[]{(byte) 0, (byte) 1, (byte) 0, (byte) 0});
            dataOutputStream.flush();

            anwser = dataInputStream.readNBytes(4);
            //Checks if the server answer is confirmation
            if (anwser[1] != 2){
                throw new IOException("[ERROR] [" + id + "] Server communication error!");
            }

            while (true);

        } catch (IOException  | InterruptedException exception) {
            System.out.println("[ERROR] Error with server communication!");
        } finally {
            try {
                socket.close(); //In case of server application doesn't response confirmation code
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
