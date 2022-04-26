package eapli.base.productmanagement.application.viadto;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.dto.ProductCategoryDTO;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class AddProductViaDTOController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListProductCategoryDTOService  svc = new ListProductCategoryDTOService();

    private final ProductRepository productRepository = PersistenceContext.repositories().products();
    private final ProductCategoryRepository productCategoryRepository = PersistenceContext.repositories().productCategories();

    public ProductDTO registerDish(final ProductDTO dto) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.SALES_CLERK);

        final var newDish = new ProductDTOParser(productCategoryRepository).valueOf(dto);

        return productRepository.save(newDish).toDTO();
    }

    public Iterable<ProductCategoryDTO> dishTypes() {
        return svc.activeProductTypes();
    }

}
