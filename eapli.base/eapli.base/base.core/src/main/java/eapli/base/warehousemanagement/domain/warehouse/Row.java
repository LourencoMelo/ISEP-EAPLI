package eapli.base.warehousemanagement.domain.warehouse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Row {

    /**
     * Id of the Row
     */
    @Id
    private long id;

    /**
     * Begin of the Row
     */
    private Begin begin;

    /**
     * End of the Row
     */
    private End end;

    /**
     * Number the shelves that divide the row
     */
    private int shelves;

    @ManyToOne
    @JoinColumn(name = "Aisle")
    private Aisle aisle;

    /**
     * Constructor of the Row
     * @param id Id of the Row
     * @param begin Begin of the Row
     * @param end End of the Row
     * @param shelves Number the shelves that divide the row
     */
    public Row(long id, Begin begin, End end, int shelves) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.shelves = shelves;
    }

    /**
     * Empty constructor
     */
    public Row() {

    }
}
