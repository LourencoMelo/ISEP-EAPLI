package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.general.domain.model.Designation;

import java.util.HashMap;
import java.util.Map;

public class JpaProductRepository extends BasepaRepositoryBase<Product, Designation, Designation> implements ProductRepository {

    JpaProductRepository() {
        super("name");
    }


    @Override
    public Iterable<Product> findByActive(boolean active) {
        return match("e.active = true");
    }

    @Override
    public Iterable<Product> findByCategory(final ProductCategory category) {
        final Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        return match("e.product.category = :category", params);
    }

}
