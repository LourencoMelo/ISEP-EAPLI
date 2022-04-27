package eapli.base.warehousemanagement.domain.warehouse;

import eapli.framework.domain.model.ValueObject;

public class Accessibility implements ValueObject {

    /**
     * Accessibility
     */
    private String accessibility;

    /**
     * Constructor
     * @param accessibility
     */
    public Accessibility(String accessibility) {
        this.setAccessibility(accessibility);
    }

    /**
     * Setter of the Accessibility
     * @param accessibility
     */
    public void setAccessibility(String accessibility) {
        try{
            if(accessibility.equalsIgnoreCase("l+") ||
                    accessibility.equalsIgnoreCase("l-") ||
                    accessibility.equalsIgnoreCase("w+") ||
                    accessibility.equalsIgnoreCase("w-")){
                this.accessibility = accessibility;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("The accessibility wasn't accepted! \n" +
                    "Isn't the accepted ones: l+, l-, w+, w-");
        }
    }
}
