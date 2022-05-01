package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.Product;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

@UseCaseController
public class ListProductController {

    private final ListProductService svc = new ListProductService();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Iterable<Product> activeProducts() {
        return svc.activeProducts();
    }

    public Optional<Product> findById(final Long id){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return svc.findById(id);
    }


}
