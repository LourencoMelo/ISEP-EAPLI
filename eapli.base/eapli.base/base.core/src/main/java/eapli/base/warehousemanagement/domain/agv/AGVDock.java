package eapli.base.warehousemanagement.domain.agv;

import eapli.base.warehousemanagement.domain.warehouse.Accessibility;
import eapli.base.warehousemanagement.domain.warehouse.Begin;
import eapli.base.warehousemanagement.domain.warehouse.Depth;
import eapli.base.warehousemanagement.domain.warehouse.End;

public class AGVDock {

    /**
     * Id of the AGVDock
     */
    private String id;

    /**
     * Where the AGV begins
     */
    private Begin begin;

    /**
     * The AGV ends
     */
    private End end;

    /**
     * Amount of depth
     */
    private Depth depth;

    /**
     * Accessibility of the AGVDock
     */
    private Accessibility accessibility;

    /**
     * Constructor for the AGV Dock
     * @param id
     * @param begin
     * @param end
     * @param depth
     * @param accessibility
     */
    public AGVDock(String id, Begin begin, End end, Depth depth, Accessibility accessibility) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
    }

    /**
     * Empty Constructor
     */
    public AGVDock() {

    }

    @Override
    public String toString() {
        return "AGVDock{" +
                "id='" + id + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", depth=" + depth +
                ", accessibility=" + accessibility +
                '}';
    }

    public Begin retrievePosition(){
        return begin;
    }

    public String retrieveId(){
        return id;
    }
}
