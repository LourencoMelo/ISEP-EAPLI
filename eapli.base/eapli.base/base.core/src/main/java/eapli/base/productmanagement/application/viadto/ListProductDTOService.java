package eapli.base.productmanagement.application.viadto;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class ListProductDTOService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<ProductDTO> allProducts() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.SALES_CLERK);

        final Iterable<Product> products = this.productRepository.findAll();

        // transform for the presentation layer
        final List<ProductDTO> ret = new ArrayList<>();
        products.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }

}
