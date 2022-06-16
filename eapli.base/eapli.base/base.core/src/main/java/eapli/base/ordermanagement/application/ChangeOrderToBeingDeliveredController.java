package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.Services.ChangeAllToBeingDeliveredService;
import eapli.base.ordermanagement.Services.ChangeToBeingDeliveredService;
import eapli.base.ordermanagement.domain.Order;

public class ChangeOrderToBeingDeliveredController {

    private final ChangeAllToBeingDeliveredService changeAllToBeingDeliveredService = new ChangeAllToBeingDeliveredService();

    private final ChangeToBeingDeliveredService changeToBeingDeliveredService = new ChangeToBeingDeliveredService();

    public String changeToBeingDelivered(Order order) {
        if(changeToBeingDeliveredService.changeToBeingDelivered(order)){
            return "Succesfull";
        }else{
            return "Error changing to being delivered order";
        }
    }

    public String changeAllToBeingDelivered() {
        if(changeAllToBeingDeliveredService.changeAllToBeingDelivered()){
            return "Succesfull";
        }else{
            return "Error changing to being delivered order";
        }
    }

    public Iterable<Order> ordersDispatched(){
        return changeAllToBeingDeliveredService.ordersDispatched();
    }

}