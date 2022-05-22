package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Cash;
import eapli.framework.domain.model.ValueObject;
import lombok.*;

import javax.persistence.*;

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
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "Shipment_price")),
            @AttributeOverride(name = "currency", column = @Column(name = "Shipment_currency")),
    })
    private Cash price;

    protected ShipmentMethod() {

    }

    @Override
    public String toString() {
        return name;
    }
}
