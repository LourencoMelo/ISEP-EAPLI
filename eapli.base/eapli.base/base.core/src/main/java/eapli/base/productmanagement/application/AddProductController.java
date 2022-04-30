package eapli.base.productmanagement.application;

import com.fasterxml.jackson.databind.ser.Serializers;
import eapli.base.customermanagement.domain.Address;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.HashSet;
import java.util.Set;

public class AddProductController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListProductCategoryService svcProductCategories = new ListProductCategoryService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ProductBuilder productBuilder;

    public AddProductController() {
        productBuilder = new ProductBuilder();
    }

    public Photo donePhoto(byte[] photo){
        return productBuilder.donePhoto(photo);
    }

    public Product addProduct(ProductCategory productCategory, String name, String shortDescription
            , String extendedDescription, String technicalDescription, String brand, String reference
            , double unitaryPreTaxPrice, double unitaryPosTaxPrice, String formatBarCode, long barcode
            , int productionCode, Set<Photo> photos) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);
        if(photos == null) {

            photos = new HashSet<>();

            final var newProduct = new ProductBuilder()
                    .ofType(productCategory)
                    .named(Designation.valueOf(name))
                    .shortDescriptioned(shortDescription)
                    .extendedDescriptioned(extendedDescription)
                    .technicalDescriptioned(technicalDescription)
                    .branded(brand)
                    .referenced(reference)
                    .preTaxcosting(Cash.euros(unitaryPreTaxPrice))
                    .posTaxcosting(Cash.euros(unitaryPosTaxPrice))
                    .makingBarcode(formatBarCode, barcode)
                    .makingProductionCode(productionCode)
                    .withPhotos(photos)
                    .build();

            return productRepository.save(newProduct);
        } else {
            final var newProduct = new ProductBuilder()
                    .ofType(productCategory)
                    .named(Designation.valueOf(name))
                    .shortDescriptioned(shortDescription)
                    .extendedDescriptioned(extendedDescription)
                    .technicalDescriptioned(technicalDescription)
                    .branded(brand)
                    .referenced(reference)
                    .preTaxcosting(Cash.euros(unitaryPreTaxPrice))
                    .posTaxcosting(Cash.euros(unitaryPosTaxPrice))
                    .makingBarcode(formatBarCode, barcode)
                    .makingProductionCode(productionCode)
                    .withPhotos(photos)
                    .build();

            return productRepository.save(newProduct);
        }

    }

    public Iterable<ProductCategory> productCategories() {
        return svcProductCategories.activeProductCategories();
    }

}
