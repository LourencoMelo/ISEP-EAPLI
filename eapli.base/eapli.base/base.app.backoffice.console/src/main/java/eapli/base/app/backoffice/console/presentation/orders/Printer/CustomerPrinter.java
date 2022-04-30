package eapli.base.app.backoffice.console.presentation.orders.Printer;

import eapli.base.customermanagement.domain.Customer;
import eapli.framework.visitor.Visitor;

public class CustomerPrinter implements Visitor<Customer> {

    @Override
    public void visit(Customer visitee) {
        System.out.printf("%-30s%-25s",  visitee.getName(),visitee.getEmail());
    }
}
