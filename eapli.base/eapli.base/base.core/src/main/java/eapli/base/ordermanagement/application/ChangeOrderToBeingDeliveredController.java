package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class ChangeOrderToBeingDeliveredController {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<Order> ordersDispatched() {
        return orderRepository.ordersDispatched();
    }

    public String changeToBeingDelivered(Order order) {
    try {
        order.isBeingDelivered();
        orderRepository.save(order);
        return "Succesfull";
    } catch (Exception e) {
        return "Error changing to being delivered order";
    }
}

    public String changeAllToBeingDelivered() {
        try {
            for (Order order : ordersDispatched()) {
                order.isBeingDelivered();
                orderRepository.save(order);
            }
            return "Succesfull";
        } catch (Exception e) {
            return "ERROR changing to being delivered every order";
        }
    }

}