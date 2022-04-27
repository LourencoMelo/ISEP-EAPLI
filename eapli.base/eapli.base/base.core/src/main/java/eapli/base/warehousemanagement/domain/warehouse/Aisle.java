package eapli.base.warehousemanagement.domain.warehouse;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aisle {

    /**
     * Id of the Aisle
     */
    @Id
    private long id;

    /**
     * Where the Aisle begins
     */
    private Begin begin;

    /**
     * Where the Aisle ends
     */
    private End end;

    /**
     * The depth of the Aisle
     */
    private Depth depth;

    /**
     * Accessibility of the Aisle
     */
    private Accessibility accessibility;

    /**
     * Rows of the Aisle
     */
    @OneToMany(mappedBy = "aisle", cascade = CascadeType.ALL)
    private List<Row> listRow;

    @ManyToOne
    @JoinColumn(name = "WareHouse")
    private WareHousePlant wareHousePlant;

    /**
     * Constructor of the Aisle
     * @param id Id of the Aisle
     * @param begin Where the Aisle begins
     * @param end Where the Aisle ends
     * @param depth The depth of the Aisle
     * @param accessibility Accessibility of the Aisle
     */
    public Aisle(long id, Begin begin, End end, Depth depth, Accessibility accessibility) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
    }

    public void setListRow(List<Row> listRow) {
        this.listRow = listRow;
    }

    /**
     * Empty Constructor
     */
    public Aisle() {

    }
}
