package eapli.base.warehousemanagement.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class WareHousePlant {

    private String description;

    private int length;

    private int width;

    private int square;

    private String unit;

    @OneToMany
    private Aisle aisle;

    @OneToMany
    private AGVDock agvDock;

    public WareHousePlant(String description, int length, int width, int square, String unit, Aisle aisle, AGVDock agvDock) {
        this.description = description;
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
        this.aisle = aisle;
        this.agvDock = agvDock;
    }
}
