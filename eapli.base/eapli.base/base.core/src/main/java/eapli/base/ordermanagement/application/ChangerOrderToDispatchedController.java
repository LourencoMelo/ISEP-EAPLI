package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class ChangerOrderToDispatchedController {


    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<Order> ordersPrepared() {
        return orderRepository.ordersPrepared();
    }

    public Iterable<Order> ordersToBePaid() {
        return orderRepository.ordersToBePaid();
    }

    public String changeToDispatched(Order order) {
        try {
            order.isDispatched();
            orderRepository.save(order);
            return "Succesfull";
        } catch (Exception e) {
            return "Error dispatching order";
        }
    }

    public String changeAllToDispatched() {
        try {
            for (Order order : ordersPrepared()) {
                order.isDispatched();
                orderRepository.save(order);
            }
            return "Succesfull";
        } catch (Exception e) {
            return "ERROR dispatching every order";
        }
    }

}
