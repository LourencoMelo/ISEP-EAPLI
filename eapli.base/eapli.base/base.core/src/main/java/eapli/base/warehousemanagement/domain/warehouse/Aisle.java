package eapli.base.warehousemanagement.domain.warehouse;

import java.util.List;


public class Aisle {

    /**
     * Id of the Aisle
     */
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
    private List<Row> listRow;

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
        this.listRow = null;
    }

    public void setListRow(List<Row> listRow) {
        this.listRow = listRow;
    }

    /**
     * Empty Constructor
     */
    public Aisle() {

    }

    @Override
    public String toString() {
        return "Aisle{" +
                "id=" + id +
                ", begin=" + begin +
                ", end=" + end +
                ", depth=" + depth +
                ", accessibility=" + accessibility +
                ", listRow=" + listRow +
                '}';
    }
}
