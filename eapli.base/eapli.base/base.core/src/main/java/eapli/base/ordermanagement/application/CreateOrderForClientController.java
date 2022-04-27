package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.repositories.OrderRepository;

/**
 * Controller to create new order on behalf a given client
 */
public class CreateOrderForClientController {

    /**
     * Instance to the repository
     */
    private final OrderRepository repository = PersistenceContext.repositories().orders();



}
