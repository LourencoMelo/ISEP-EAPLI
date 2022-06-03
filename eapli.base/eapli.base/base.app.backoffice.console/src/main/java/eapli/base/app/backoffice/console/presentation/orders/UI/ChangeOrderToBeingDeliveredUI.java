package eapli.base.app.backoffice.console.presentation.orders.UI;

import eapli.base.app.backoffice.console.presentation.orders.Printer.OrderPrinter;
import eapli.base.ordermanagement.application.ChangeOrderToBeingDeliveredController;
import eapli.base.ordermanagement.domain.Order;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ChangeOrderToBeingDeliveredUI extends AbstractUI {

    private final ChangeOrderToBeingDeliveredController controller = new ChangeOrderToBeingDeliveredController();

    @Override
    protected boolean doShow() {
        try {

            final Iterable<Order> ordersDispatched = this.controller.ordersDispatched();
            boolean moreOrders;

            int changeOrNot = Console.readInteger("Do you want to change one or more orders to being delivered " +
                    "or just want to see all the dispatched orders? 0 -> View the list ; 1 -> Change; 2 -> END");

            switch (changeOrNot) {

                case 0:
                    System.out.println("List of orders that are prepared to start to being delivered: ");
                    System.out.println(ordersDispatched);
                    break;
                case 1:
                    System.out.println("List of orders that are prepared to start to being delivered: ");
                    System.out.println(ordersDispatched);

                    int select = Console.readInteger("Do you want to change all dispatched orders to being delivered? \n" +
                            "If not, you will have to change one by one: 0 -> YES ; 1 -> NO; 2 -> END");
                    switch (select) {
                        case 0:
                            controller.changeAllToBeingDelivered();
                            System.out.println("All orders started to being delivered!");
                            break;
                        case 1:
                            do {
                                changeOrderToBeingDelivered(this.controller.ordersDispatched());
                                System.out.println("Order being delivered successfully!");
                                moreOrders = wantToChangeMoreOrder();
                            } while (moreOrders);
                            System.out.println("All orders that you wanted started to being delivered!");
                            break;
                        case 2:
                            break;
                    }
                    break;
            }
            return true;
        } catch (
                Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public String headline() {
        return "Change order to being delivered";
    }

    private void changeOrderToBeingDelivered(Iterable<Order> ordersDispatched) {
        final SelectWidget<Order> selector = new SelectWidget<>("Orders:", ordersDispatched,
                new OrderPrinter());
        selector.show();
        final Order theOrder = selector.selectedElement();

        controller.changeToBeingDelivered(theOrder);

    }

    private boolean wantToChangeMoreOrder() {
        int select2 = Console.readInteger("Do you want to change another order to " +
                "being delivered? 0 -> YES ; 1 -> NO");
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