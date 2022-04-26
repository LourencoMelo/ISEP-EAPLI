package eapli.base.productmanagement.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@DTO
@Data
@AllArgsConstructor
public class ChangeProductPricesDTO {

    private String name;
    private double priceBeforeTaxes;
    private double priceAfterTaxes;
    private String currency;

}
