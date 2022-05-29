package eapli.base.dashboard.domain;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HttpClient extends Thread{

    String baseFolder;
    SSLSocket socket;
    DataInputStream inputStream;
    DataOutputStream dataOutputStream;

    public HttpClient(SSLSocket s, String file){
        socket = s;
        baseFolder = file;
    }

    @Override
    public void run() {
        try {
         dataOutputStream = new DataOutputStream(socket.getOutputStream());
         inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
