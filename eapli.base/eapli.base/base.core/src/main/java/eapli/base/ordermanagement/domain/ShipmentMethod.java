package eapli.base.ordermanagement.domain;

import eapli.framework.general.domain.model.Money;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ShipmentMethod {

    /**
     * Name of the shipment method
     */
    @NonNull
    private String name;

    /**
     * Price of the shipment method
     */
    @NonNull
    private Money price;

}
