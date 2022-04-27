package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaProductCategoryRepository extends JpaAutoTxRepository<ProductCategory, Long,  String> implements ProductCategoryRepository {

    public JpaProductCategoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "code");
    }

    public JpaProductCategoryRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "code");
    }

    @Override
    public Iterable<ProductCategory> activeProductCategories() {
        return match("e.active=true");
    }

}
