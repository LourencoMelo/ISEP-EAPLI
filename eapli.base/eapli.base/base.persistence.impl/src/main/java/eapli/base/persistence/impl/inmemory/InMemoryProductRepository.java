package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryProductRepository extends InMemoryDomainRepository<Product, Designation> implements ProductRepository {


    static{
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Product> findByActive(boolean active) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Product> findByCategory(ProductCategory category){
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }
}
