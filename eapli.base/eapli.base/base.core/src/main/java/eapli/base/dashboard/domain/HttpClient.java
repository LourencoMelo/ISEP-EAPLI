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

        try {
            HTTPmessage request = new HTTPmessage(inputStream);
            //Salta para as exceções ocasionalmente
            HTTPmessage response = new HTTPmessage();

            if(request.getMethod().equals("GET")){
                if(request.getURI().equals("/info")){
                    //Não acede ao método refresh table
                    response.setContentFromString(HttpServer.refreshTable(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else {
                    String fullname = baseFolder + "/";
                    if (request.getURI().equals("/")) {
                        fullname = fullname + "index.html";
                    } else {
                        fullname = fullname + request.getURI();
                    }
                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString("<html><body><h1>404 File not found</h1></body></html>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                    response.send(dataOutputStream);
                }
            }else{
                if(request.getMethod().equals("PUT") && request.getURI().startsWith("/info")){
                    response.setResponseStatus("200 Ok");
                }else{
                    response.setContent("<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>", "text/html");
                    response.setResponseStatus("405 Method Not Allowed");
                }
            }

        } catch (IOException ioException) {
            System.out.println("[Error] Io exception!");;
        }

        try {
            socket.close();
        }catch (IOException ioException){
            System.out.println("[Error] Couldn't close socket!");;

        }
    }
}
