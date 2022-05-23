package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.base.ordermanagement.domain.Order;
import eapli.base.warehousemanagement.application.ForceOrderOnAGVController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Optional;

public class ForceOrderOnAGVUI extends AbstractUI{

    public long orderPk;
    public String agvId;


    private final ForceOrderOnAGVController forceOrderOnAGVController;

    public ForceOrderOnAGVUI() {
        this.forceOrderOnAGVController = new ForceOrderOnAGVController();
    }

    @Override
    protected boolean doShow() {
        System.out.println("============ Orders ===========");
        forceOrderOnAGVController.retrieveOrdersPrepared();
        System.out.println("===============================");
        this.orderPk = Console.readLong("Insert the Order Identifier");
        Optional<Order> order = forceOrderOnAGVController.findOrderById(orderPk);
        System.out.println("============ AGVs =============");

        System.out.println("===============================");
        this.agvId = Console.readLine("Insert the AGV Id");


        return false;
    }

    @Override
    public String headline() {
        return "Force Order on AGV";
    }
}
