package eapli.base.customermanagement.application;

import eapli.base.customermanagement.domain.Customer;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class ListCustomersController {

    private ListCustomerService service = new ListCustomerService();

    public Iterable<Customer> allCustomers(){
        return service.allCustomers();
    }
}
