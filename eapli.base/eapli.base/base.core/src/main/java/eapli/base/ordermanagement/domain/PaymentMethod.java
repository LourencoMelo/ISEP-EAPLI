package eapli.base.ordermanagement.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class PaymentMethod {

    /**
     * Name of the payment method
     */
    @NonNull
    private String name;

}
