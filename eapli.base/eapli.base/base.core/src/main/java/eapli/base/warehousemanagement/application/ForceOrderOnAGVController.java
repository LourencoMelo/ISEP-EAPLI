package eapli.base.warehousemanagement.application;

import eapli.base.ordermanagement.Services.ListOrderToBePreparedService;
import eapli.base.ordermanagement.Services.FindOrderByIdService;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.Services.FindAGVByIdService;
import eapli.base.warehousemanagement.Services.FindAGVReadyService;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;
import java.util.Optional;

public class ForceOrderOnAGVController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ListOrderToBePreparedService listOrderToBePreparedService;
    private final FindOrderByIdService findOrderByIdService;
    private final FindAGVByIdService findAGVByIdService;
    private final FindAGVReadyService findAGVReadyService;

    public ForceOrderOnAGVController() {
        this.listOrderToBePreparedService = new ListOrderToBePreparedService();
        this.findOrderByIdService = new FindOrderByIdService();
        this.findAGVByIdService = new FindAGVByIdService();
        this.findAGVReadyService = new FindAGVReadyService();
    }

    public Iterable<Order> retrieveOrdersPrepared(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);

        return listOrderToBePreparedService.ordersToBePrepared();
    }

    public List<Order> findOrderById(long orderId){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);

        return findOrderByIdService.findOrderById(orderId);
    }

    public List<AGV> findAGVById(String agvId){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);

        return findAGVByIdService.findAGVById(agvId);
    }

    public Iterable<AGV> findAvailableAGVS(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);

        return findAGVReadyService.findAvailableAGVS();
    }
}
