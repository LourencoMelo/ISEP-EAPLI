package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

@UseCaseController
public class ListProductController {

    private final ListProductService svc = new ListProductService();
    private final ListProductCategoryService svcProductCategories = new ListProductCategoryService();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Iterable<Product> activeProducts() {
        return svc.activeProducts();
    }

    public Iterable<Product> findByCategory(ProductCategory category) {
        return svc.findByCategory(category);
    }

    public Iterable<Product> findByBrand(Designation brand) {
        return svc.findByBrand(brand);
    }

    public Iterable<Product> findByDescription(Description description) {
        return svc.findByDescription(description);
    }

    public Optional<Product> findById(final Long id){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return svc.findById(id);
    }

    public Iterable<ProductCategory> productCategories() {
        return svcProductCategories.activeProductCategories();
    }


}
