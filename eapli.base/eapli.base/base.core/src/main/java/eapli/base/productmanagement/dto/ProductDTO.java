package eapli.base.productmanagement.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productCategory;

    private String name;

    private String shortDescription;

    private String extendedDescription;

    private String technicalDescription;

    private String brand;

    private String reference;

    private boolean active;

    private double prePrice;

    private double posPrice;

    private String volume;

    private String weight;

    private int row;

    private int aisle;

}
