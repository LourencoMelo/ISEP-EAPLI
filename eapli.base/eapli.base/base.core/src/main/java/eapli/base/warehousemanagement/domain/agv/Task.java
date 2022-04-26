package eapli.base.warehousemanagement.domain.agv;

import javax.persistence.*;

@Entity
public class Task {

    /**
     * ID of the task
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The AGV Responsible for the Task
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agvId", referencedColumnName = "id")
    private AGV agvResp;

    /**
     * List of Products to be collected
     */
    //private List<Products> listProducts;

    /**
     * Status of the Task
     */
    private Status status;

    /**
     * Constructor of the task
     * @param id
     * @param agvResp
     * @param status
     */
    public Task(long id, AGV agvResp, Status status) {
        this.id = id;
        this.agvResp = agvResp;
        this.status = status;
    }

    /**
     * Empty constructor
     */
    public Task() {

    }
}
