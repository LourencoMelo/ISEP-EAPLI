package eapli.base.ordermanagement.domain;

import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.productmanagement.domain.Cash;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;

import java.util.Calendar;
import java.util.Map;

public class OrderBuilder implements DomainFactory<Order> {

    private Order theOrder;
    private Map<Product, Integer> products;

    private Address billingAddress;

    private Address deliveringAddress;

    private PaymentMethod paymentMethod;

    private ShipmentMethod shipmentMethod;

    private EmailAddress clerksEmail;

    private String method;

    private Calendar interactionDate;

    private String comment;

    private Cash priceBeforeTaxes;

    private Cash priceAfterTaxes;

    private Customer customer;


    public OrderBuilder withProducts(final Map<Product, Integer> products) {
        this.products = products;
        return this;
    }

    public OrderBuilder withBillingAddress(final Address address) {
        this.billingAddress = address;
        return this;
    }

    public OrderBuilder withDeliveringAddress(final Address address) {
        this.deliveringAddress = address;
        return this;
    }

    public OrderBuilder withPaymentMethodChoosen(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OrderBuilder withShipmentMethodChoosen(final ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
        return this;
    }

    public OrderBuilder withSalesClerkEmail(final String email) {
        return emailed(EmailAddress.valueOf(email));
    }

    public OrderBuilder emailed(final EmailAddress email) {
        this.clerksEmail = email;
        return this;
    }

    public OrderBuilder usedMethod(final String method) {
        this.method = method;
        return this;
    }

    public OrderBuilder onTheDate(final Calendar interactionDate) {
        this.interactionDate = interactionDate;
        return this;
    }

    public OrderBuilder commented(final String comment) {
        this.comment = comment;
        return this;
    }

    public OrderBuilder withPriceWithoutTaxes(final Cash price) {
        this.priceBeforeTaxes = price;
        return this;
    }

    public OrderBuilder withPriceWithTaxes(final Cash price) {
        this.priceAfterTaxes = price;
        return this;
    }

    public OrderBuilder associatedTo(final Customer customer){
        this.customer = customer;
        return this;
    }

    public Order buildOrThrow() {
        if (theOrder != null) {
            return theOrder;
        } else if (clerksEmail != null) {
            theOrder = new Order(products, billingAddress, deliveringAddress, paymentMethod, shipmentMethod, priceBeforeTaxes, priceAfterTaxes, clerksEmail, method, interactionDate, comment, customer);
            return theOrder;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * @return
     */
    @Override
    public Order build() {
        final Order ret = buildOrThrow(); //Ensures that we will create a new instance

        theOrder = null;
        return ret;
    }
}
