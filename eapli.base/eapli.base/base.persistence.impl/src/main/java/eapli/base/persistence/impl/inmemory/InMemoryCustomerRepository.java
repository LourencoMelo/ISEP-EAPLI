package eapli.base.persistence.impl.inmemory;

import eapli.base.costumermanagement.domain.Customer;
import eapli.base.costumermanagement.repositories.CustomerRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, Long>
        implements CustomerRepository {

    static {
        InMemoryInitializer.init();
    }

}
