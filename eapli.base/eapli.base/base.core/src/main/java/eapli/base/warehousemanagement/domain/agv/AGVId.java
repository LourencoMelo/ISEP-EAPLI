package eapli.base.warehousemanagement.domain.agv;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

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
    public String toString() {
        return "AGVId{" +
                "agvId='" + agvId + '\'' +
                '}';
    }

    @Override
    public int compareTo(AGVId o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AGVId agvId1 = (AGVId) o;
        return Objects.equals(agvId, agvId1.agvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agvId);
    }
}
