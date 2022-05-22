package eapli.base.persistence.impl.inmemory;

import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryOrderRepository extends InMemoryDomainRepository<Order, Long> implements OrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Order> ordersToBePaid() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Order> ordersToBePrepared() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Order> ordersPrepared() {
        throw new UnsupportedOperationException();
    }
}
