package eapli.base.dashboard.application;

import eapli.base.dashboard.domain.HttpServer;
import eapli.base.warehousemanagement.Services.FindAllAGVService;
import eapli.base.warehousemanagement.domain.agv.AGV;

public class ShowDashboardController {

    private final FindAllAGVService findAllAGVService = new FindAllAGVService();

    public void showDashboard() {
        HttpServer server = new HttpServer(findAllAGVService.findAll());
        server.changeController(this);
        server.start();
    }

    public Iterable<AGV> getAllAgv(){
        return findAllAGVService.findAll();
    }
}
