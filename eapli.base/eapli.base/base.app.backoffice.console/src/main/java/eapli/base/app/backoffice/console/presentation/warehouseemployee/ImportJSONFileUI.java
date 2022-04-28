package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;


public class ImportJSONFileUI extends AbstractUI {


    private String fileName;

    @Override
    protected boolean doShow() {
        this.fileName = Console.readLine("Insert the file name:");

        return false;
    }

    @Override
    public String headline() {
        return "Import Json File:";
    }
}
