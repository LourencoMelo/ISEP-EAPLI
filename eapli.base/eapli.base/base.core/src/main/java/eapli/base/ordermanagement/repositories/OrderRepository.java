package eapli.base.ordermanagement.repositories;

import eapli.base.ordermanagement.domain.Order;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface OrderRepository extends DomainRepository<Long, Order> {

    Iterable<Order> ordersToBePaid();

    Iterable<Order> ordersToBePrepared();

    Iterable<Order> ordersPrepared();

    Optional<Order> findOrderById(Long id);
}
