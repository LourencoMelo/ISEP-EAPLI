package eapli.base.ordermanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class ChangeAllToBeingDeliveredService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public Iterable<Order> ordersDispatched() {
        return orderRepository.ordersDispatched();
    }

    public boolean changeAllToBeingDelivered(){
        try {
            for (Order order : ordersDispatched()) {
                order.isBeingDelivered();
                orderRepository.save(order);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
