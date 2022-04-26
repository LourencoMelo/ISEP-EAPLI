package eapli.base.warehousemanagement.domain.warehouse;

import eapli.base.warehousemanagement.domain.agv.AGVDock;
import eapli.framework.general.domain.model.Description;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

public class WareHousePlant {

    /**
     * Description of the WareHousePlant
     */
    private Description description;

    /**
     * The length of the WareHouse
     */
    private int length;

    /**
     * The width of the Warehouse
     */
    private int width;

    /**
     * Square dimensions of the warehouse
     */
    private int square;

    /**
     * The unit in use(meters, yards...)
     */
    private String unit;

    /**
     * Aisles of the Warehouse
     */
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Aisle> listAisle;

    /**
     * AGVDock of the Warehouse
     */
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<AGVDock> listAGVDock;

    /**
     * Constructor of the Warehouse
     * @param description Description of the WareHousePlant
     * @param length The length of the WareHouse
     * @param width The width of the Warehouse
     * @param square Square dimensions of the warehouse
     * @param unit The unit in use(meters, yards...)
     * @param listAisle Aisles of the Warehouse
     * @param listAGVDock AGVDock of the Warehouse
     */
    public WareHousePlant(Description description, int length, int width, int square, String unit, List<Aisle> listAisle, List<AGVDock> listAGVDock) {
        this.description = description;
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
        this.listAisle = listAisle;
        this.listAGVDock = listAGVDock;
    }
}
