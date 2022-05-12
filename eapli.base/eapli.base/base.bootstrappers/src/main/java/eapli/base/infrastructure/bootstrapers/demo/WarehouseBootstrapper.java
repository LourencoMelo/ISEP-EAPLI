package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.warehousemanagement.domain.methods.ImportJSONFile;
import eapli.base.warehousemanagement.domain.warehouse.WareHousePlant;
import eapli.framework.actions.Action;

import java.io.File;

public class WarehouseBootstrapper implements Action{

    @Override
    public boolean execute() {
        ImportJSONFile importJSONFile = new ImportJSONFile();
        File file = new File("Files/warehouse1.json");
        WareHousePlant wareHousePlant = importJSONFile.ImportJSONFile(file);
        System.out.println("Imported Default JSON File!");
        return true;
    }
}
