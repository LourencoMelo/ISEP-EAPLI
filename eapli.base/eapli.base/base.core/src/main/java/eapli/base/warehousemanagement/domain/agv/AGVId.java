package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

public class AGVId implements ValueObject {

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
}
