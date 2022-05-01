package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        return match("e.category = :category", params);
    }

    @Override
    public Iterable<Product> findByBrand(final Designation brand) {
        final Map<String, Object> params = new HashMap<>();
        params.put("brand", brand);
        return match("e.brand = :brand", params);
    }

    @Override
    public Iterable<Product> findByDescription(final Description shortDescription) {
        final Map<String, Object> params = new HashMap<>();
        params.put("shortDescription", shortDescription);
        return match("e.shortDescription = :shortDescription", params);
    }

    @Override
    public Optional<Product> findById(final Long id) {
        final TypedQuery<Product> query = entityManager().createQuery(
                "SELECT p FROM Product p WHERE p.id = :parameter", Product.class);
        query.setParameter("parameter",  id);
        return Optional.ofNullable(query.getSingleResult());
    }

}
