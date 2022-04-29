package eapli.base.productmanagement.application.viadto;

import eapli.base.productmanagement.domain.Cash;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductBuilder;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import eapli.framework.representations.dto.DTOParser;

public class ProductDTOParser implements DTOParser<ProductDTO, Product> {

    private final ProductCategoryRepository productCategoryRepository;

    /**
     * Configure the parser by injecting the necessary dependency.
     *
     * @param dishTypeRepository the dish type repository
     */
    public ProductDTOParser(final ProductCategoryRepository dishTypeRepository) {
        this.productCategoryRepository = dishTypeRepository;
    }

    @Override
    public Product valueOf(final ProductDTO dto) {
        // retrieve the dish type
        final ProductCategory type = productCategoryRepository.ofIdentity(String.valueOf((dto.getName())))
                .orElseThrow(() -> new IllegalArgumentException("Unknown product category: " + dto.getName()));

        return new ProductBuilder().ofType(type).named(dto.getName())
                .shortDescriptioned(dto.getShortDescription()).extendedDescriptioned(dto.getExtendedDescription())
                .technicalDescriptioned(dto.getTechnicalDescription()).branded(dto.getBrand())
                .referenced(dto.getReference()).preTaxcosting(Cash.dollars(dto.getPrePrice()))
                .posTaxcosting(Cash.dollars(dto.getPosPrice())).build();
    }
}
