package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@ApplicationService
public class ListProductCategoryService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductCategoryRepository productCategoryRepository = PersistenceContext.repositories().productCategories();

    public Iterable<ProductCategory> allProductCategories() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        return productCategoryRepository.findAll();
    }

    public Iterable<ProductCategory> activeProductCategories() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        return productCategoryRepository.activeProductCategories();
    }

}
