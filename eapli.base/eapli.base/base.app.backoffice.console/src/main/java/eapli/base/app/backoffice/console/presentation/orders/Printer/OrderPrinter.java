package eapli.base.app.backoffice.console.presentation.orders.Printer;

import eapli.base.ordermanagement.domain.Order;
import eapli.framework.visitor.Visitor;

public class OrderPrinter implements Visitor<Order> {
    @Override
    public void visit(Order visitee) {
        System.out.println(visitee.toString());
    }
}
