package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class PaymentMethod implements ValueObject {

    /**
     * Name of the payment method
     */
    @NonNull
    private String name;

    protected PaymentMethod(){
        //Empty
    }

}
