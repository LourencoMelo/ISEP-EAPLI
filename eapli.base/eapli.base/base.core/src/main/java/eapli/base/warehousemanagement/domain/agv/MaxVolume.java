package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class MaxVolume implements ValueObject {

    /**
     * MaxVolume
     */
    private double maxVolume;

    /**
     * Constructor for MaxVolume
     * @param maxVolume
     */
    public MaxVolume(double maxVolume) {
        this.setMaxVolume(maxVolume);
    }

    public MaxVolume() {

    }

    /**
     * Setter for MaxVolume
     * @param maxVolume
     */
    public void setMaxVolume(double maxVolume) {
        try{
            if(maxVolume > 0){
                this.maxVolume = maxVolume;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("The Max Volume needs to be higher than 0");
        }
    }
}
