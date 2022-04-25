package eapli.base.costumermanagement.application;

import eapli.base.costumermanagement.domain.Customer;
import eapli.base.costumermanagement.domain.CustomerBuilder;
import eapli.base.costumermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;

/**
 * Controller for the register customer use case
 */
public class RegisterCustomerController {

    /**
     * Instance of customer builder
     */
    private final CustomerBuilder customerBuilder;

    /**
     * Instance of customer repository
     */
    private final CustomerRepository customerRepository;

    private final UserManagementService userManagementService = AuthzRegistry.userService();

    /**
     * Instance of customer
     */
    private Customer customer;

    public RegisterCustomerController(){
        customerBuilder = new CustomerBuilder();
        customerRepository = PersistenceContext.repositories().customers();
    }

}
