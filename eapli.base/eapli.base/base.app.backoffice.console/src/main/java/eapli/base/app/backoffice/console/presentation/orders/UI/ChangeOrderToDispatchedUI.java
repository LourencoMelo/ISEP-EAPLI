package eapli.base.app.backoffice.console.presentation.orders.UI;

import eapli.base.app.backoffice.console.presentation.orders.Printer.OrderPrinter;
import eapli.base.ordermanagement.application.ChangerOrderToDispatchedController;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ChangeOrderToDispatchedUI extends AbstractUI {

    private final ChangerOrderToDispatchedController controller = new ChangerOrderToDispatchedController();

    @Override
    protected boolean doShow() {
        try {

            final Iterable<Order> ordersPrepared = this.controller.ordersPrepared();
            boolean moreOrders;

            System.out.println("List of orders that are prepared to be dispatched: ");
            System.out.println(ordersPrepared);

            int select = Console.readInteger("Do you want to change all prepared orders to dispatched? \n" +
                    "If not, you will have to change one by one: 0 -> YES ; 1 -> NO; 2 -> END");
            switch (select) {
                case 0:
                    controller.changeAllToDispatched();
                    System.out.println("All orders were dispatched!");
                    break;
                case 1:
                    do {
                        changeOrderToDispatched(ordersPrepared);
                        System.out.println("Order dispatched successfully!");
                        moreOrders = wantToChangeMoreOrder();
                    } while (moreOrders);
                    System.out.println("All orders that you wanted were successfully dispatched!");
                    break;
                case 2:
                    break;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String headline() {
        return "Change order to be dispatched";
    }

    private void changeOrderToDispatched(Iterable<Order> ordersPrepared) {
        final SelectWidget<Order> selector = new SelectWidget<>("Orders:", ordersPrepared,
                new OrderPrinter());
        selector.show();
        final Order theOrder = selector.selectedElement();

        controller.changeToDispatched(theOrder);

    }

    private boolean wantToChangeMoreOrder() {
        int select2 = Console.readInteger("Do you want to change another order to " +
                "dispatched? 0 -> YES ; 1 -> NO");
        switch (select2) {
            case 0:
                return true;
            case 1:
                return false;
            default:
                System.out.println("Command does not exist");
                return false;
        }
    }
}
