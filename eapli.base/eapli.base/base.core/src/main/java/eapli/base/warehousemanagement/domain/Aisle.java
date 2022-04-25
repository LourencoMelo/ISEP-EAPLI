package eapli.base.warehousemanagement.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Aisle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Aisle(long id) {
        this.id = id;
    }
}
