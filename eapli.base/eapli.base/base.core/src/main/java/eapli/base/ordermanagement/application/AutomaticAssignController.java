package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.Services.AutomaticAssignOrderService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;

public class AutomaticAssignController {

    /**
     * Instance to ensure the presence of the required roles for the task
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final SystemUser currentUser = authz.session().get().authenticatedUser();

    private final AutomaticAssignOrderService automaticAssignOrderService = new AutomaticAssignOrderService();

    public void automaticAssignment(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE, BaseRoles.POWER_USER, BaseRoles.ADMIN);
        try{
            this.automaticAssignOrderService.automaticAssign(currentUser, 7);
        }catch (IOException e){
            throw new IllegalArgumentException(e.getMessage());
        }

    }
}
