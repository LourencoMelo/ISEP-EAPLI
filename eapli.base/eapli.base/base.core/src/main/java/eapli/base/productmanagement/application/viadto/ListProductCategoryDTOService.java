package eapli.base.productmanagement.application.viadto;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.dto.ProductCategoryDTO;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ApplicationService
public class ListProductCategoryDTOService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductCategoryRepository productCategoryRepository = PersistenceContext.repositories()
            .productCategories();

    public Iterable<ProductCategoryDTO> allDishTypes() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.SALES_CLERK);

        final Iterable<ProductCategory> types = this.productCategoryRepository.findAll();
        return transformToDTO(types);
    }

    private Iterable<ProductCategoryDTO> transformToDTO(final Iterable<ProductCategory> types) {
        return StreamSupport.stream(types.spliterator(), true)
                .map(ProductCategory::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }

    public Iterable<ProductCategoryDTO> activeProductTypes() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.SALES_CLERK);

        return transformToDTO(this.productCategoryRepository.activeProductCategories());
    }

}
