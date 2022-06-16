package eapli.base.ordermanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class ChangeToBeingDeliveredService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public boolean changeToBeingDelivered(Order order){
        try {
            order.isBeingDelivered();
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
