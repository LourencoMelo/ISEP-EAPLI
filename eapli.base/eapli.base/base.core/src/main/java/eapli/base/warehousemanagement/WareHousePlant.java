package eapli.base.warehousemanagement;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class WareHousePlant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int length;

    private int square;

    private String unit;

    @OneToMany
    private Aisle aisle;

    public WareHousePlant(long id, int length, int square, String unit, Aisle aisle) {
        this.id = id;
        this.length = length;
        this.square = square;
        this.unit = unit;
        this.aisle = aisle;
    }
}
