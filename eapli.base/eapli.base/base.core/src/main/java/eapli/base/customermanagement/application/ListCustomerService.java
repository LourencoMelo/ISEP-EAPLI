package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListCustomerService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public Iterable<Customer> allCustomers(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.SALES_CLERK);

        return customerRepository.findAll();
    }
}
