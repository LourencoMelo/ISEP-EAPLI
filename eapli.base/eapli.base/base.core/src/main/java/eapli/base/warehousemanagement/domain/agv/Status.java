package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

public class Status implements ValueObject {

    /**
     * Status description
     */
    private String statusDescription;

    /**
     * Status constructor
     * @param statusDescription
     */
    public Status(String statusDescription) {
        this.setStatusDescription(statusDescription);
    }

    /**
     * Setter for the status description
     * @param statusDescription
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
