package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@ApplicationService
public class ListOrderToBePreparedService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<Order> ordersToBePrepared() {
        return this.orderRepository.ordersToBePrepared();
    }

}
