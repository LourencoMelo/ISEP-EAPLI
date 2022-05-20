package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.methods.ImportJSONFile;
import eapli.base.warehousemanagement.domain.warehouse.WareHousePlant;

import java.io.File;

public class ImportJSONFileController {

    private ImportJSONFile importJSONFile;

    public ImportJSONFileController(){
        this.importJSONFile = new ImportJSONFile();
    }

    public WareHousePlant importJsonFileController(File file){
        return importJSONFile.ImportJSONFile(file);
    }
}
