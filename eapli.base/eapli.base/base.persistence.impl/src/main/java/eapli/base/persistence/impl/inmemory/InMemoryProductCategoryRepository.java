package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProductCategoryRepository extends InMemoryDomainRepository<ProductCategory, String> implements ProductCategoryRepository {


    @Override
    public Iterable<ProductCategory> activeProductCategories() {
        return null;
    }
}
