package eapli.base.dashboard.domain;

import eapli.base.dashboard.application.ShowDashboardController;
import eapli.base.warehousemanagement.domain.agv.AGV;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpServer extends Thread{

    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";

    static final int PORT = 55091;
//    static private SSLServerSocket sslServerSocket;

    static private ServerSocket sslServerSocket;


    //    static final String TRUSTED_STORE = "serverHTTP.jks";
//    static final String keyStorePassProperties = "forgotten";
    private static ShowDashboardController showDashboardController;

    private static Iterable<AGV> list;

    public HttpServer(Iterable<AGV> agvs) {
        list = agvs;
    }

    public void changeController(ShowDashboardController showDashboardController){
        this.showDashboardController = showDashboardController;
    }

    @Override
    public void run() {
//        SSLSocket sslSocket = null;

        Socket clisocket = null;

//        // TRUSTED_STORE -> "serverHTTP.jks"
//        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
//
//        // KEYSTORE_PASS -> "forgotten"
//        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        try {
//            SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

//            sslServerSocket =  sslServerSocketFactory.createServerSocket(PORT);
            sslServerSocket =  new ServerSocket(PORT);

        } catch (IOException ioException) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while (true){
            try{
//                sslSocket = (SSLSocket) sslServerSocket.accept();
                clisocket = sslServerSocket.accept();
            } catch (IOException ioException) {
                System.out.println("[INFO] Connection blocked");
            }
            HttpClient req = new HttpClient(clisocket, BASE_FOLDER);
            req.start();
        }

    }

    public static synchronized String refreshTable(){
        try {
            if(list != null){
                StringBuilder s = new StringBuilder();
                for(AGV agv : showDashboardController.getAllAgv()){
                    s.append("<tr class=\"active-row\">" +
                            "<td style=\"color:black\">" + agv.identity().toString() + "</td>" +
                            "<td style=\"color:black\">" + agv.getPosition()+ "</td>" +
                            "<td style=\"color:black\">" + agv.getStatus()+ "</td>" +
                            "</tr>");
                }
                return s.toString();
            }else {
                return " ";
            }
        }catch (Exception exception){
            /**
             * Mudar esta mensagem
             */
            System.out.println("[INFO] Connection blocked!");
            return " ";
        }
    }
}
