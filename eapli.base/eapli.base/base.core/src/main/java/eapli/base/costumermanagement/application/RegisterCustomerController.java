package eapli.base.costumermanagement.application;

import eapli.base.costumermanagement.domain.*;
import eapli.base.costumermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.HashSet;
import java.util.Set;

import java.util.Date;

/**
 * Controller for the register customer use case
 */
public class RegisterCustomerController {

    /**
     * Instance of customer repository
     */
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    private final UserManagementService userManagementService = AuthzRegistry.userService();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final CustomerBuilder customerBuilder;

    public RegisterCustomerController(){
        customerBuilder = new CustomerBuilder();
    }

    public Address addressed(String streetName, int doorNumber, String postalCode, String city, String country){
        return customerBuilder.addressed(streetName,doorNumber,postalCode,city,country);
    }


    public Customer registerCustomer(final String firstName, final String lastName, final String email, final String gender,
                                     final long phone, final String vat, final int day,
                                     final int month, final int year,final Set<Address> addressSet) {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK);

        final var newCustomer = new CustomerBuilder()
                .named(firstName,lastName)
                .emailed(email)
                .gendered(gender)
                .phoned(phone)
                .vated(vat)
                .dated(year,month,day)
                .withAddresses(addressSet)
                .build();

                return customerRepository.save(newCustomer);
    }

    /*public void saveCustomerAsUserWithCredentials(){
        Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER);
        userManagementService.registerNewUser(colab.email().email, colab.palavraPasse().rawPassword, colab.nomeCurto().primeiroNome, colab.nomeCurto().ultimoNome, colab.email().email, rolesColab);
        colaboradorRepository.save(colab);
    }*/





}
