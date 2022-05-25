package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.base.ordermanagement.domain.Order;
import eapli.base.warehousemanagement.application.ForceOrderOnAGVController;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;
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
        Iterable<Order> orders = forceOrderOnAGVController.retrieveOrdersPrepared();
        for(Order order : orders){
            System.out.println(order);
        }
        System.out.println("===============================");

        this.orderPk = Console.readLong("Insert the Order Identifier");
        List<Order> order = forceOrderOnAGVController.findOrderById(orderPk);
        System.out.println("============ AGVs =============");
        Iterable<AGV> agvs = forceOrderOnAGVController.findAvailableAGVS();
        for(AGV agv : agvs){
            System.out.println(agv);
        }
        System.out.println("===============================");

        this.agvId = Console.readLine("Insert the AGV Id");
        AGV agvForced = forceOrderOnAGVController.findAGVById(agvId);


        return false;
    }

    @Override
    public String headline() {
        return "Force Order on AGV";
    }
}
