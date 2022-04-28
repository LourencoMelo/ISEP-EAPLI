package eapli.base.ordermanagement.domain;

import eapli.base.customermanagement.domain.Address;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.general.domain.model.Money;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.*;

@Entity
public class Order implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Numeric identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Version
    private Long version;

    /**
     * Ordered products with their respective quantities
     */
    @ElementCollection
    private Map<Product, Integer> orderedProducts;

    /**
     * Date of the registration
     */
    @Temporal(TemporalType.DATE)
    private Calendar registDate;

    /**
     * Billing address
     */
    @Embedded
    private Address billingAddress;

    /**
     * Delivering address
     */
    @Embedded
    private Address deliveringAddress;

    /**
     * Email from clerk who created order
     */
    @Embedded
    private EmailAddress clerkEmail;

    /**
     * Method of communication between sales clerk and respective client
     */
    private String method;

    /**
     * Date when the interaction happened
     */
    private Date interactionDate;

    /**
     * Additional comment about the interaction
     */
    private String comment;

    /**
     * Total price before taxes
     */
    @Embedded
    private Money priceBeforeTaxes;

    /**
     * Total price after taxes
     */
    @Embedded
    private Money priceAfterTaxes;

    /**
     * Current status from the order
     */
    private OrderStatus status;

    public Address getDeliveringAddress() {
        return deliveringAddress;
    }

    public void setDeliveringAddress(Address deliveringAddress) {
        this.deliveringAddress = deliveringAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    protected Order() {
        //Empty
    }

    /**
     * Constructor used when order is created by a customer
     *
     * @param products         map of products from shopping cart
     * @param billing          address for billing
     * @param delivering       address for delivering
     * @param paymentMethod    method of payment
     * @param shipmentMethod   method of shipment
     * @param priceBeforeTaxes price without taxes applied
     * @param priceAfterTaxes  price with taxes applied
     */
    public Order(Map<Product, Integer> products, Address billing, Address delivering, PaymentMethod paymentMethod, ShipmentMethod shipmentMethod, Money priceBeforeTaxes, Money priceAfterTaxes) {
        this.orderedProducts = products;
        this.billingAddress = billing;
        this.deliveringAddress = delivering;
        this.registDate = Calendar.getInstance();
        this.priceBeforeTaxes = priceBeforeTaxes;
        this.priceAfterTaxes = priceAfterTaxes;
        this.clerkEmail = null;
        this.method = "";
        this.interactionDate = null;
        this.comment = "";
        this.status = OrderStatus.PAYMENT_PENDING;
    }

    /**
     * Constructor used when order is created by a sales clerk on behalf of a given customer
     *
     * @param products         map of products inserted at the time
     * @param billing          address for billing
     * @param delivering       address for delivering
     * @param paymentMethod    method of payment
     * @param shipmentMethod   method of shipment
     * @param priceBeforeTaxes price without taxes applied
     * @param priceAfterTaxes  price with taxes applied
     * @param clerkEmail       email of the sales clerk responsible for the order creation
     * @param method           method of communication between sales clerk and respective client
     * @param interactionDate  date of the interaction
     * @param comment          additional comment
     */
    public Order(Map<Product, Integer> products, Address billing, Address delivering, PaymentMethod paymentMethod, ShipmentMethod shipmentMethod, Money priceBeforeTaxes, Money priceAfterTaxes, EmailAddress clerkEmail, String method, Date interactionDate, String comment) {
        this.orderedProducts = products;
        this.billingAddress = billing;
        this.deliveringAddress = delivering;
        this.registDate = Calendar.getInstance();
        this.priceBeforeTaxes = priceBeforeTaxes;
        this.priceAfterTaxes = priceAfterTaxes;
        this.clerkEmail = clerkEmail;
        this.method = method;
        this.interactionDate = interactionDate;
        this.comment = comment;
        this.status = OrderStatus.PAYMENT_PENDING;
    }

    /////////////////////////////////////////////////////////////Status Management//////////////////////////////////////////////////////////////


    public OrderStatus status(){
        return this.status;
    }

    public boolean isPaid(){
        return status() == OrderStatus.PAID;
    }

    public boolean isDispatched(){
        return status() == OrderStatus.DISPATCHED;
    }

    public boolean isDelivered(){
        return status() == OrderStatus.DELIVERED;
    }

    public boolean isUndelivered(){
        return status() == OrderStatus.UNDELIVERED;
    }

    public boolean isCanceled(){
        return status() == OrderStatus.CANCELED;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param other order
     * @return true if same
     */
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Order)) {
            return false;
        }
        final Order that = (Order) other;
        return new EqualsBuilder().append(pk, that.pk).append(billingAddress, that.billingAddress)
                .append(deliveringAddress, that.deliveringAddress).append(orderedProducts, that.orderedProducts).append(status, that.status)
                .isEquals();
    }

    /**
     * @return id
     */
    @Override
    public Long identity() {
        return pk;
    }
}
