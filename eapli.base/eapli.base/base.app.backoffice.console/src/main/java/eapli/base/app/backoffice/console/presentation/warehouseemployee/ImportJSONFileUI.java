package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.base.warehousemanagement.application.ImportJSONFileController;
import eapli.base.warehousemanagement.domain.methods.ImportJSONFile;
import eapli.base.warehousemanagement.domain.warehouse.WareHousePlant;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.io.util.Console;

import java.io.File;


public class ImportJSONFileUI extends AbstractUI {


    private String filePath;

    @Override
    protected boolean doShow() {
        this.filePath = Console.readLine("Insert the absolute file path:");
        File file = new File(filePath);
        ImportJSONFileController importJSONFileController = new ImportJSONFileController();
        WareHousePlant wareHousePlant = importJSONFileController.importJsonFileController(file);
        System.out.println("\n" + wareHousePlant);

        return false;
    }

    @Override
    public String headline() {
        return "Import Json File:";
    }
}
