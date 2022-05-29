package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.base.app.backoffice.console.presentation.orders.Printer.OrderPrinter;
import eapli.base.ordermanagement.application.AssignOrderController;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.warehousemanagement.application.ForceOrderOnAGVController;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.List;
import java.util.Optional;

public class ForceOrderOnAGVUI extends AbstractUI {

    AssignOrderController controller = new AssignOrderController();

    public ForceOrderOnAGVUI() {
    }


    @Override
    protected boolean doShow() {
        final Iterable<Order> ordersPrepared = this.controller.ordersToBePrepared();

        System.out.println("Please choose an order :");

        final SelectWidget<Order> selectWidget = new SelectWidget<>("Orders :", ordersPrepared, new OrderPrinter());

        selectWidget.show();

        Order order = selectWidget.selectedElement();

        this.controller.assignOrder(order.getPk());

        return false;
    }

    @Override
    public String headline() {
        return "Force Order on AGV";
    }
}
