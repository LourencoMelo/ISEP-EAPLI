package eapli.base.warehousemanagement.application;

import eapli.base.ordermanagement.Services.ListOrderToBePreparedService;
import eapli.base.ordermanagement.Services.FindOrderByIdService;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Optional;

public class ForceOrderOnAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ListOrderToBePreparedService listOrderToBePreparedService;
    private final FindOrderByIdService findOrderByIdService;

    public ForceOrderOnAGVController() {
        this.listOrderToBePreparedService = new ListOrderToBePreparedService();
        this.findOrderByIdService = new FindOrderByIdService();
    }

    public Iterable<Order> retrieveOrdersPrepared(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);

        return listOrderToBePreparedService.ordersToBePrepared();
    }

    public Optional<Order> findOrderById(long orderId){
        return findOrderByIdService.findOrderById(orderId);
    }
}
