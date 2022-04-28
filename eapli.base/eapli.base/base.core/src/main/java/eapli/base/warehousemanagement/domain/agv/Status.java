package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
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

    public Status() {

    }

    /**
     * Setter for the status description
     * @param statusDescription
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
