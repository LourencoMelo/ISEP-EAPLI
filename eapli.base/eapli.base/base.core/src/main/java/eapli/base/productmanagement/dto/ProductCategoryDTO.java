package eapli.base.productmanagement.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategoryDTO {

    private String alphanumericCode;

    private String description;

}
