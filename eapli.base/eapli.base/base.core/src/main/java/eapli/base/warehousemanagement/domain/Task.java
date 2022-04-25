package eapli.base.warehousemanagement.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private AGV agvResp;

    //private List<Products> listProducts;

    private String status;
}
