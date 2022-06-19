package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.base.dashboard.application.ShowDashboardController;
import eapli.framework.presentation.console.AbstractUI;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ShowDashboardUI extends AbstractUI {

    private final ShowDashboardController showDashboardController = new ShowDashboardController();

    @Override
    protected boolean doShow() {
        showDashboardController.showDashboard();

        URI uri;
        try {
            uri = new URI("http://localhost:55091/");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Dashboard";
    }
}
