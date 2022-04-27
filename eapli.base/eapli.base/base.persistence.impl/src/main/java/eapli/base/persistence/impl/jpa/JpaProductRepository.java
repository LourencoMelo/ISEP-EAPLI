package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.general.domain.model.Designation;

import javax.persistence.TypedQuery;

public class JpaProductRepository extends BasepaRepositoryBase<Product, Designation, Designation> implements ProductRepository {

    JpaProductRepository() {
        super("name");
    }


}
