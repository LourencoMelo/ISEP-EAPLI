package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class ActivateDeactivateProductController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListProductService svc = new ListProductService();
    private final ProductRepository dishRepository = PersistenceContext.repositories().products();

    public Iterable<Product> allProducts() {
        return this.svc.allProducts();
    }

    public Product changeProductState(final Product product) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK,
                BaseRoles.POWER_USER);
        Preconditions.nonNull(product);

        product.toogleState();

        return dishRepository.save(product);
    }
}
