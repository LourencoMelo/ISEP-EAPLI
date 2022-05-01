package eapli.base.warehousemanagement.domain.warehouse;

public class Row {

    /**
     * Id of the Row
     */
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

    @Override
    public String toString() {
        return "Row{" +
                "id=" + id +
                ", begin=" + begin +
                ", end=" + end +
                ", shelves=" + shelves +
                '}';
    }
}
