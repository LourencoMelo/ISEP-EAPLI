package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.domain.repositories.LockableDomainRepository;

public interface ProductCategoryRepository
        extends DomainRepository<String, ProductCategory>, LockableDomainRepository<String, ProductCategory> {


    Iterable<ProductCategory> activeProductCategories();


}
