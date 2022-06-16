package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.Services.AssignOrderService;
import eapli.base.ordermanagement.Services.ListOrderToBePreparedService;
import eapli.base.ordermanagement.domain.Order;
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

    private final ListOrderToBePreparedService listOrderToBePreparedService = new ListOrderToBePreparedService();

    public void assignOrder(Long orderID){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER);
        try{
            this.assignOrderService.assignOrderToAGV(currentUser, 5, orderID);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Iterable<Order> ordersToBePrepared() {
        return listOrderToBePreparedService.ordersToBePrepared();
    }
}
