package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Cash;
import eapli.framework.domain.model.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class ShipmentMethod implements ValueObject {

    /**
     * Name of the shipment method
     */
    @NonNull
    private String name;

    /**
     * Price of the shipment method
     */
    @NonNull
    @Embedded
    private Cash price;

    protected ShipmentMethod() {

    }
}
