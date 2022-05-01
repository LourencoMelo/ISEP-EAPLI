package eapli.base.ordermanagement.application;

import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderBuilder;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.ordermanagement.domain.ShipmentMethod;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.productmanagement.domain.Cash;
import eapli.base.productmanagement.domain.Product;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.Calendar;
import java.util.Map;

/**
 * Controller to create new orders on behalf a given client
 */
public class CreateOrderForClientController {

    /**
     * Instance to the repository of orders
     */
    private final OrderRepository repository = PersistenceContext.repositories().orders();

    /**
     * Instance to ensure the presence of the required roles for the task
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Order registerOrder(final Map<Product, Integer> products, final Address billing, final Address delivering, final PaymentMethod paymentMethod,
                               final ShipmentMethod shipmentMethod, final String method, final Calendar interactionDate, final String comment, final Customer customer){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_CLERK);

        final var newOrder = new OrderBuilder()
                .withProducts(products)
                .withBillingAddress(billing)
                .withDeliveringAddress(delivering)
                .withPaymentMethodChoosen(paymentMethod)
                .withShipmentMethodChoosen(shipmentMethod)
                .withSalesClerkEmail(authz.session().get().authenticatedUser().email().toString())
                .usedMethod(method)
                .onTheDate(interactionDate)
                .commented(comment)
                .withPriceWithoutTaxes(priceWithoutTaxes(products))
                .withPriceWithTaxes(priceWithTaxes(products))
                .associatedTo(customer)
                .build();

        return repository.save(newOrder);
    }

    public Address createAddress(String streetName, int doorNumber, String postalCode, String city, String country){
        return new Address(streetName, doorNumber, postalCode, city, country);
    }

    public Calendar createCalendarDate(int year, int month, int day){
        Calendar cal1 = Calendar.getInstance();
        cal1.set(year, month, day);
        return cal1;
    }

    private Cash priceWithoutTaxes(final Map<Product, Integer> items){
        double totalAmount = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            double price = entry.getKey().getPrePrice().amountAsDouble() * entry.getValue();
           totalAmount += price;
        }

        return Cash.euros(totalAmount);

    }

    private Cash priceWithTaxes(final Map<Product, Integer> items){
        double totalAmountWithTaxes = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            double price = entry.getKey().getPosPrice().amountAsDouble() * entry.getValue();
            totalAmountWithTaxes += price;
        }

        return Cash.euros(totalAmountWithTaxes);
    }

}
