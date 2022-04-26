package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.general.domain.model.Description;

import javax.persistence.*;

@Entity
public class AGV {

    /**
     * Id of the AGV
     */
    @Id
    private AGVId id;

    /**
     * A short description
     */
    private Description description;

    /**
     * Model of the AGV
     */
    private Model model;

    /**
     * MaxWeight it can carry
     */
    private MaxWeight maxWeight;

    /**
     * MaxVolume it can Carry
     */
    private MaxVolume maxVolume;

    /**
     * Status of the AGV
     */
    private Status status;

    /**
     * Position of the AGV
     */
    private Position position;

    /**
     * His autonomy in minutes
     */
    private AutonomyMin autonomyMin;

    /**
     * His AGV Dock where it charges
     */
    @OneToOne(mappedBy = "AGVDock")
    private AGVDock agvDock;

    /**
     * His task
     */
    @OneToOne(mappedBy = "Task")
    private Task task;


    /**
     * AGV Constructor
     * @param id
     * @param description
     * @param model
     * @param maxWeight
     * @param maxVolume
     * @param status
     * @param position
     * @param autonomyHours
     */
    public AGV(AGVId id, Description description, Model model, MaxWeight maxWeight, MaxVolume maxVolume, Status status, Position position, AutonomyMin autonomyHours) {
        this.id = id;
        this.setDescription(description);
        this.model = model;
        this.maxWeight = maxWeight;
        this.maxVolume = maxVolume;
        this.status = status;
        this.position = position;
        this.autonomyMin = autonomyHours;
    }

    /**
     * Empty Constructor
     */
    public AGV() {

    }

    /**
     * Business Restrictions in description
     * @param description
     */
    public void setDescription(Description description) {
        try{
            if (description.length() < 30){
                this.description = description;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("The description of the AGV should be less than 30");
        }

    }

}
