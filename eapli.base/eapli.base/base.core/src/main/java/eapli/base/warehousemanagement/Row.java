package eapli.base.warehousemanagement;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int shelves;

}
