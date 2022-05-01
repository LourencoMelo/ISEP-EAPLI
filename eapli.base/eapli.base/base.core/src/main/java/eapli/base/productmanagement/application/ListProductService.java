package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

public class ListProductService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<Product> allProducts() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.SALES_CLERK);

        return productRepository.findAll();
    }

    public Iterable<Product> activeProducts() {
        return this.productRepository.findByActive(true);
    }

    public Iterable<Product> findByCategory(ProductCategory category){
        return this.productRepository.findByCategory(category);
    }

    public Iterable<Product> findByBrand(Designation brand){
        return this.productRepository.findByBrand(brand);
    }

    public Iterable<Product> findByDescription(Description description){
        return this.productRepository.findByDescription(description);
    }

    public Optional<Product> findById(final Long id){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK, BaseRoles.POWER_USER, BaseRoles.ADMIN);
        return productRepository.findById(id);
    }

}
