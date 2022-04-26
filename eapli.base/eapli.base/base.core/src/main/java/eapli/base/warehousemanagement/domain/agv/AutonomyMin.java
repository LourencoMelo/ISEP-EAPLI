package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

public class AutonomyMin implements ValueObject {

    /**
     * Autonomy in minutes
     */
    private int autonomyMin;

    /**
     * Autonomy Constructor
     * @param autonomyMin
     */
    public AutonomyMin(int autonomyMin) {
        this.setAutonomyMin(autonomyMin);
    }

    /**
     * Setter for autonomyMin
     * @param autonomyMin
     */
    public void setAutonomyMin(int autonomyMin) {
        try{
            if(autonomyMin > 0){
                this.autonomyMin = autonomyMin;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("The Autonomy needs to be higher than 0");
        }
    }
}
