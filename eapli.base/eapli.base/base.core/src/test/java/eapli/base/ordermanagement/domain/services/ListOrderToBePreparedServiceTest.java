package eapli.base.ordermanagement.domain.services;

import eapli.base.customermanagement.application.RegisterCustomerController;
import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.Services.ListOrderToBePreparedService;
import eapli.base.ordermanagement.application.AssignOrderController;
import eapli.base.ordermanagement.application.CreateOrderForClientController;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.ordermanagement.domain.ShipmentMethod;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.productmanagement.application.ListProductController;
import eapli.base.productmanagement.domain.Cash;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.springframework.transaction.TransactionSystemException;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ListOrderToBePreparedServiceTest {

    ListOrderToBePreparedService listOrderToBePreparedService = new ListOrderToBePreparedService();
    AssignOrderController assignOrderController = new AssignOrderController();
    private final ListProductController catalogController = new ListProductController();
    final RegisterCustomerController registerCustomerController = new RegisterCustomerController();
    final CreateOrderForClientController orderController = new CreateOrderForClientController();
    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    /**
    @Test
    public void foundOrderByIdService(){
        Map<Product, Integer> map1 = new HashMap<>();
        Map<Product, Integer> map2 = new HashMap<>();

        Optional<Product> product = catalogController.findById(9L);
        final int quantityProduct = 1;
        if(product.isPresent()){
            map1.put(product.get(), quantityProduct);
        }else{
            product.ifPresent(value -> map1.put(value, quantityProduct));
        }

        product = catalogController.findById(11L);
        final int quantityMap2 = 2;
        if (product.isPresent()) {
            map2.put(product.get(), quantityMap2);
        } else product.ifPresent(value -> map2.put(value, quantityMap2));

        Address addressBilling = new Address("Street AAA", 11, "4440-322", "Porto", "Portugal");
        Address addressDelivering = new Address("Street BBB", 22, "4440-440", "Porto", "Portugal");
        PaymentMethod paymentMethod = new PaymentMethod("Card");
        ShipmentMethod shipmentMethod = new ShipmentMethod("Car", new Cash(30, null));

        Customer customer = registerCustomerController.registerCustomer("Joao", "Beires", "joao@gmail.com", "Male", 911108522L, "432112345", 12,3,2002,null);

        Optional<Order> order1 = registerOrder(map1, addressBilling, addressDelivering, paymentMethod, shipmentMethod, "method", null, "comment", customer);
        Optional<Order> order2 = registerOrder(map2, addressBilling, addressDelivering, paymentMethod, shipmentMethod, "method", null, "comment", customer);

        order1.ifPresent(this::changeToPaid);
        order2.ifPresent(this::changeToPaid);

        Iterable<Order> ordersFind = listOrderToBePreparedService.ordersToBePrepared();
        Order orderex1 = order1.get();
        Order orderex2 = order2.get();

        Iterable<Order> ordersExpected = Arrays.asList(orderex1, orderex2);

        assertEquals(ordersExpected, ordersFind);
    }

    private Optional<Order> registerOrder(final Map<Product, Integer> products, final Address billing, final Address delivering, final PaymentMethod paymentMethod,
                                          final ShipmentMethod shipmentMethod, final String method, final Calendar interactionDate, final String comment, final Customer customer) {

        try {
            return Optional.of(
                    orderController.registerOrder(products, billing, delivering, paymentMethod, shipmentMethod, method, interactionDate, comment, customer));
        } catch (final IntegrityViolationException | ConcurrencyException
                | TransactionSystemException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated object
            return Optional.empty();
        }

    }

    public boolean changeToPaid(Order order){
        try{
            order.makePaid();
            orderRepository.save(order);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    **/

}
