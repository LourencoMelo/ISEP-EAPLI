package eapli.base.warehousemanagement.domain.agv;

import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Begin;
import eapli.base.warehousemanagement.domain.Depth;
import eapli.base.warehousemanagement.domain.End;

import javax.persistence.*;

@Entity
public class AGVDock {

    /**
     * Id of the AGVDock
     */
    @Id
    private long id;

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
     * The AGV that can charge there
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agvId", referencedColumnName = "id")
    private AGV agvResp;

    /**
     * Constructor for the AGV Dock
     * @param id
     * @param begin
     * @param end
     * @param depth
     * @param accessibility
     */
    public AGVDock(long id, Begin begin, End end, Depth depth, Accessibility accessibility) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
        this.agvResp = null;
    }

    /**
     * Empty Constructor
     */
    public AGVDock() {

    }
}
