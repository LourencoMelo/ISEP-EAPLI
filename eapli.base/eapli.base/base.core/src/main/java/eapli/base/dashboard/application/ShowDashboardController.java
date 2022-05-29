package eapli.base.dashboard.application;

import eapli.base.dashboard.domain.HttpServer;

public class ShowDashboardController {

    public void showDashboard() {
        HttpServer server = new HttpServer();
        server.changeController(this);
        server.start();
    }
}
