package eapli.base.warehousemanagement.domain.warehouse;

import eapli.base.warehousemanagement.domain.agv.AGVDock;

import java.util.List;


public class WareHousePlant{

    /**
     * Description of the WareHousePlant
     */
    private String description;

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
    private List<Aisle> listAisle;

    /**
     * AGVDock of the Warehouse
     */
    private List<AGVDock> listAGVDock;

    /**
     * Constructor of the Warehouse
     * @param description Description of the WareHousePlant
     * @param length The length of the WareHouse
     * @param width The width of the Warehouse
     * @param square Square dimensions of the warehouse
     * @param unit The unit in use(meters, yards...)
     */
    public WareHousePlant(String description, int length, int width, int square, String unit, List<Aisle> listAisle, List<AGVDock> listAGVDock) {
        this.setDescription(description);
        this.length = length;
        this.width = width;
        this.square = square;
        this.unit = unit;
        this.listAisle = listAisle;
        this.listAGVDock = listAGVDock;
    }

    /**
     * Setting description
     * @param description
     */
    public void setDescription(String description) {
        try{
            if(!description.isEmpty() && description.length() < 50){
                this.description = description;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("WareHouse description is empty or greater than 50");
        }
    }

    /**
     * Setter for List of Aisles
     * @param listAisle
     */
    public void setListAisle(List<Aisle> listAisle) {
        this.listAisle = listAisle;
    }

    /**
     * Setter for List of AGVDock
     * @param listAGVDock
     */
    public void setListAGVDock(List<AGVDock> listAGVDock) {
        this.listAGVDock = listAGVDock;
    }

    /**
     * Empty Constructor
     */
    public WareHousePlant() {

    }

    @Override
    public String toString() {
        return "WareHousePlant{" +
                "description='" + description + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", square=" + square +
                ", unit='" + unit + '\'' +
                ", listAisle=" + listAisle +
                ", listAGVDock=" + listAGVDock +
                '}';
    }
}
