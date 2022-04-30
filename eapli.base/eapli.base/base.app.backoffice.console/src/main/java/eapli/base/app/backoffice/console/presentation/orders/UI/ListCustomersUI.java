package eapli.base.app.backoffice.console.presentation.orders.UI;

import eapli.base.customermanagement.application.ListCustomersController;
import eapli.base.customermanagement.domain.Customer;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListCustomersUI extends AbstractListUI<Customer> {

    private ListCustomersController controller = new ListCustomersController();

    @Override
    protected Iterable<Customer> elements() {
        return controller.allCustomers();
    }

    @Override
    protected Visitor<Customer> elementPrinter() {
        return null;
    }

    @Override
    protected String elementName() {
        return "Customer";
    }

    @Override
    protected String listHeader() {
        return "Customers";
    }

    @Override
    protected String emptyMessage() {
        return "null";
    }

    @Override
    public String headline() {
        return "List Customers";
    }
}
