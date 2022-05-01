package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface ProductRepository extends DomainRepository<Designation, Product> {

    Iterable<Product> findByActive(boolean active);

    Iterable<Product> findByCategory(ProductCategory productCategory);

    Iterable<Product> findByBrand(Designation brand);

    Iterable<Product> findByDescription(Description description);

    Optional<Product> findById(Long id);

}
