package eapli.base.app.backoffice.console.presentation.orders.UI;

import eapli.base.app.backoffice.console.presentation.orders.Printer.OrderPrinter;
import eapli.base.ordermanagement.application.AssignOrderController;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;



public class AssignOrderUI extends AbstractUI {

    AssignOrderController controller = new AssignOrderController();

    @Override
    protected boolean doShow() {

        try {

            final Iterable<Order> ordersPrepared = this.controller.ordersToBePrepared();

            System.out.println("Please choose an order :");

            final SelectWidget<Order> selectWidget = new SelectWidget<>("Orders :", ordersPrepared, new OrderPrinter());

            selectWidget.show();

            Order order = selectWidget.selectedElement();

            this.controller.assignOrder(order.getPk());

            return false;
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Assign Order To Agv";
    }
}
