package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.application.ImportJSONFileController;
import eapli.base.warehousemanagement.domain.warehouse.WareHousePlant;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;

public class ConfigureAGVUI extends AbstractUI {

    public String id;
    public String description;
    public String model;
    public double maxWeight;
    public double maxVolume;
    public String status;
    public String agvDockId;
    public int x;
    public int y;
    public int autonomyMin;

    private final ConfigureAGVController configureAGVController;
    private final ImportJSONFileController importJSONFileController;

    public ConfigureAGVUI() {
        this.configureAGVController = new ConfigureAGVController();
        this.importJSONFileController = new ImportJSONFileController();
    }

    @Override
    protected boolean doShow() {
        this.id = Console.readLine("Insert the Id:");
        this.description = Console.readLine("Insert the description:");
        this.model = Console.readLine("Insert the model:");
        this.maxWeight = Console.readDouble("Insert his max weight:");
        this.maxVolume = Console.readDouble("Insert his max volume:");
        this.status = Console.readLine("Insert his status:");

        /**
         * Creating a controller to AGVDOCK definition
         */
        File file = new File("Files/warehouse1.json");
        WareHousePlant wareHouse = importJSONFileController.importJsonFileController(file);
        System.out.println("========== AGV DOCKS ==========");




        this.x = Console.readInteger("Insert his x position:");
        this.y = Console.readInteger("Insert his y position:");
        this.autonomyMin = Console.readInteger("Insert the autonomy in minutes:");

        try{
            this.configureAGVController.configureAGV(id,description,model,maxWeight,maxVolume,status,x,y,autonomyMin);
        }catch (@SuppressWarnings("unused") final IntegrityViolationException e){
            System.out.println("You tried to specify an AGV that already exists in the database");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Configure AGV:";
    }
}
