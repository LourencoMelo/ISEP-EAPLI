package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

public class MaxWeight implements ValueObject {

    /**
     * MaxWeight
     */
    private double maxWeight;


    /**
     * Constructor for the MaxWeight
     * @param maxWeight
     */
    public MaxWeight(double maxWeight) {
        this.setMaxWeight(maxWeight);
    }

    /**
     * Setter for the Weight
     * @param maxWeight
     */
    public void setMaxWeight(double maxWeight) {
        try{
            if(maxWeight > 0){
                this.maxWeight = maxWeight;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("The Max Weight needs to be higher than 0");
        }
    }

}
