package eapli.base.ordermanagement.domain;


import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.ordermanagement.Services.ChangeAllToBeingDeliveredService;
import eapli.base.productmanagement.domain.*;
import eapli.framework.general.domain.model.EmailAddress;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class OrderTest {

    private final Map<Product, Integer> map = new HashMap<>();

    private final Address billing = new Address("Rua",22,"zip","city","country");
    private final Address delivering = new Address("Rua",23,"zip","city","country");

    private final PaymentMethod paymentMethod = new PaymentMethod("Paypal");

    private final ShipmentMethod shipmentMethod = new ShipmentMethod("Ford Transit", Cash.euros(40.0));

    private final EmailAddress clerksEmail = EmailAddress.valueOf("joao@gmail.com");

    private final Calendar calendar = Calendar.getInstance();

    private final Customer customer = new Customer();

    @Test
    public void newOrderFromSalesClerk(){
        new Order(map,billing,delivering,paymentMethod,shipmentMethod, Cash.euros(20), Cash.euros(25), clerksEmail,"email", calendar, "vazio", customer);
    }

    /*@Test
    public void ordersSelectedAreBeingDispatched() {
        ChangeAllToBeingDeliveredService service = new ChangeAllToBeingDeliveredService();

        Iterable<Order> orders = service.ordersDispatched();

        boolean done = true;

        for(Order order : orders) {
            if(order.status() != OrderStatus.DISPATCHED) {
                done = false;
            }
        }

        assertTrue(done);

    }**/

}
