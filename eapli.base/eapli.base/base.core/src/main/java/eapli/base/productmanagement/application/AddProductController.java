package eapli.base.productmanagement.application;

import com.fasterxml.jackson.databind.ser.Serializers;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductBuilder;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.domain.Reference;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AddProductController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListProductCategoryService svcProductCategories = new ListProductCategoryService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Product addProduct(ProductCategory productCategory, String name, String shortDescription, String extendedDescription, String technicalDescription, String brand, String reference, double unitaryPreTaxPrice, double unitaryPosTaxPrice) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        final var newProduct = new ProductBuilder()
                .ofType(productCategory)
                .named(Designation.valueOf(name))
                .shortDescriptioned(shortDescription)
                .extendedDescriptioned(extendedDescription)
                .technicalDescriptioned(technicalDescription)
                .branded(brand)
                .referenced(reference)
                .preTaxcosting(Money.euros(unitaryPreTaxPrice))
                .posTaxcosting(Money.euros(unitaryPosTaxPrice))
                .build();

        return productRepository.save(newProduct);
    }

    public Iterable<ProductCategory> productCategories() {
        return svcProductCategories.activeProductCategories();
    }



}
