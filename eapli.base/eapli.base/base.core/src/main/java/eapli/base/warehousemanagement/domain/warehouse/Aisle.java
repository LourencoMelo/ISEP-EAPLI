package eapli.base.warehousemanagement.domain.warehouse;

import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Begin;
import eapli.base.warehousemanagement.domain.Depth;
import eapli.base.warehousemanagement.domain.End;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    /**
     * Constructor of the Aisle
     * @param id Id of the Aisle
     * @param begin Where the Aisle begins
     * @param end Where the Aisle ends
     * @param depth The depth of the Aisle
     * @param accessibility Accessibility of the Aisle
     * @param listRow of the Aisle
     */
    public Aisle(long id, Begin begin, End end, Depth depth, Accessibility accessibility, List<Row> listRow) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
        this.listRow = listRow;
    }

    /**
     * Empty Constructor
     */
    public Aisle() {

    }
}
