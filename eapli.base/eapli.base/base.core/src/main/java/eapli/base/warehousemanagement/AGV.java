package eapli.base.warehousemanagement;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class AGV {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    private String model;

    private double maxWeight;

    private double maxVolume;

    private boolean status;

    private int positionX;

    private int positionY;

    private int autonomyHours;

    //@OneToOne
    //private Task task


    public AGV(long id, String description, String model, double maxWeight, double maxVolume, boolean status, int positionX, int positionY, int autonomyHours) {
        this.id = id;
        this.description = description;
        this.model = model;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;
        this.status = status;
        this.positionX = positionX;
        this.positionY = positionY;
        this.autonomyHours = autonomyHours;
    }
}
