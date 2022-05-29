package eapli.base.dashboard.domain;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.dashboard.application.ShowDashboardController;
import eapli.base.warehousemanagement.Services.FindAllAGVService;
import eapli.base.warehousemanagement.domain.agv.AGV;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;


public class HttpServer extends Thread{

    static private final String BASE_FOLDER = "base.core/src/main/java/eapli/base/dashboard/domain/www";

    static final int PORT = 8080;
    static private SSLServerSocket sslServerSocket;
    static final String TRUSTED_STORE = "serverHTTP.jks";
    static final String keyStorePassProperties = "forgotten";
    private static ShowDashboardController showDashboardController;

    /**
     * DataBase Services
     */
    static FindAllAGVService findAllAGVService = new FindAllAGVService();

    public void changeController(ShowDashboardController showDashboardController){
        this.showDashboardController = showDashboardController;
    }

    @Override
    public void run() {
        SSLSocket sslSocket = null;


        // TRUSTED_STORE -> "serverHTTP.jks"
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);

        // KEYSTORE_PASS -> "forgotten"
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        try {
            SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

            sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(PORT);
        } catch (IOException ioException) {
            System.out.println("[INFO] Connection blocked");
        }

        while (true){
            try{
                sslSocket = (SSLSocket) sslServerSocket.accept();
            } catch (IOException ioException) {
                System.out.println("[INFO] Connection blocked");
            }
            HttpClient req = new HttpClient(sslSocket, BASE_FOLDER);
            req.start();
        }

    }

    public static synchronized String refreshTable(){
        try {
            if(findAllAGVService.findAll() == null){
                Iterable<AGV>  agvResult = findAllAGVService.findAll();
                System.out.println(agvResult);
                StringBuilder s = new StringBuilder();
                for(AGV agv : agvResult){
                    s.append("<tr class=\"active-row\">" +
                            "<td>" + agv.identity().toString() + "</td>" +
                            "<td>" + agv.getPosition()+ "</td>" +
                            "<td>" + agv.getStatus()+ "</td>" +
                            "</tr>");
                }
                System.out.println("Teste #1");
                return s.toString();
            }else {
                System.out.println("Teste #2");
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
