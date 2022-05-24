package eapli;

import eapli.base.AppSettings;
import eapli.base.Application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;

import javax.net.ssl.SSLSocket;
import java.net.InetAddress;

public class TcpClientAgvDigitalTwin {


    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();
    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
//    static final int serverPortProperties = app.getServerPortKey();
//    static final String keyStorePassProperties = app.getKeysStorePassAgvManagerKey();
    static final String keyStorePassProperties = "forgotten";

    static InetAddress server_ip;
    static SSLSocket socket;

    Iterable<AGV> agvsAvailable = agvRepository.findAvailableAGVS();



}
