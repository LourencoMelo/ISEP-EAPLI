package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.Order;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrderRepository extends DomainRepository<Long, Order> {

    Iterable<Order> ordersToBePaid();

    Iterable<Order> ordersToBePrepared();

    Iterable<Order> ordersPrepared();
}
