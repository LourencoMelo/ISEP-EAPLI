package eapli.base.persistence.impl.jpa;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.base.Application;


public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, Long, Long>
        implements CustomerRepository {

    public JpaCustomerRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    public JpaCustomerRepository(TransactionalContext tx) {
        super(tx, "id");
    }
}
