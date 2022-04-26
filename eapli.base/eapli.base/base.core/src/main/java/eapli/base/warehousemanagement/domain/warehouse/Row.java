package eapli.base.warehousemanagement.domain.warehouse;

import eapli.base.warehousemanagement.domain.Begin;
import eapli.base.warehousemanagement.domain.End;

import javax.persistence.Entity;
import javax.persistence.Id;

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
