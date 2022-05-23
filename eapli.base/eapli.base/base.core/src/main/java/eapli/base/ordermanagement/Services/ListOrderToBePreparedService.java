package eapli.base.ordermanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class ListOrderToBePreparedService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<Order> ordersToBePrepared() {
        return this.orderRepository.ordersToBePrepared();
    }

}
