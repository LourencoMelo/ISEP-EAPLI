package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.warehousemanagement.application.ImportJSONFileController;
import eapli.base.warehousemanagement.domain.warehouse.WareHousePlant;
import eapli.framework.actions.Action;

import java.io.File;

public class WarehouseBootstrapper implements Action{

    @Override
    public boolean execute() {
        File file = new File("Files/warehouse1.json");
        ImportJSONFileController importJSONFileCtrl = new ImportJSONFileController();
        WareHousePlant wareHousePlant = importJSONFileCtrl.importJsonFileController(file);
        if (wareHousePlant != null){
            System.out.println("Imported Default JSON File!");
        }else{
            System.out.println("Not Imported. Object is null");
        }
        return true;
    }
}
