package eapli.base.warehousemanagement.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AGVDock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Accessibility accessibility;

    public AGVDock(long id, Accessibility accessibility) {
        this.id = id;
        this.accessibility = accessibility;
    }
}
