package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class ListOrderToBeDispatchedService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<Order> ordersPrepared() {
        return this.orderRepository.ordersPrepared();
    }

}
