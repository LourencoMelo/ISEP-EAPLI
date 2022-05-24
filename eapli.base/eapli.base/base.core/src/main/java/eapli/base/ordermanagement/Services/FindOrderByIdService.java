package eapli.base.ordermanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.framework.application.ApplicationService;

import java.util.List;
import java.util.Optional;

@ApplicationService
public class FindOrderByIdService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public List<Order> findOrderById(long orderId){
        return this.orderRepository.findOrderById(orderId);
    }
}
