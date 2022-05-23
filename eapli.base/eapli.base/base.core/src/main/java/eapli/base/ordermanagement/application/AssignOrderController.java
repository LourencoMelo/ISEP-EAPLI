package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.Services.AssignOrderService;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;

public class AssignOrderController {

    /**
     * Instance to ensure the presence of the required roles for the task
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final SystemUser currentUser = authz.session().get().authenticatedUser();

    private final AssignOrderService assignOrderService = new AssignOrderService();

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public void assignOrder(Order order){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK, BaseRoles.ADMIN);
        try{
            this.assignOrderService.assignOrderToAGV(currentUser, 5, order);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Iterable<Order> ordersPrepared() {
        return orderRepository.ordersPrepared();
    }
}
