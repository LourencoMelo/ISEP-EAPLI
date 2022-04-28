package eapli.base.persistence.impl.inmemory;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, Long>
        implements CustomerRepository {

    static {
        InMemoryInitializer.init();
    }

}
