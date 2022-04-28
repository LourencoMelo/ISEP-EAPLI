package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class AGVId implements ValueObject, Comparable<AGVId> {

    /**
     * AgvId
     */
    private String agvId;

    /**
     * Constructor for the AGVId
     * @param agvId
     */
    public AGVId(String agvId) {
        this.setAgvId(agvId);
    }

    public AGVId() {

    }

    /**
     * Setter for the AGVId
     * @param agvId
     */
    public void setAgvId(String agvId) {
        try {
            if(!agvId.isEmpty() && agvId.length() <= 8){
                this.agvId = agvId;
            }else {
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("The Id shouldn't be empty and less than 8 chars");
        }
    }

    @Override
    public int compareTo(AGVId o) {
        return 0;
    }
}
