package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListProductService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<Product> allProducts() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.SALES_CLERK);

        return productRepository.findAll();
    }

}
