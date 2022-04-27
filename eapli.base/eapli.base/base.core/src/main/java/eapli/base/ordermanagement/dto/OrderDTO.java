package eapli.base.ordermanagement.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private String billingAddress;

    private String deliveringAddress;

    private double priceBeforeTaxes;

    private double priceAfterTaxes;

    private String clerkEmail;

    private String method;

    private String comment;

}
