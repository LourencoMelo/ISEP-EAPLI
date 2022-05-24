package eapli.base.ordermanagement.domain;

import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.productmanagement.domain.Cash;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Products_Order")
public class Order implements AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Numeric identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Identity", nullable = false)
    private Long pk;

    @Version
    private Long version;

    /**
     * Ordered products with their respective quantities
     */
    @ElementCollection
    @Column(name = "Quantity")
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
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "Billing_street")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "Billing_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "Billing_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "Billing_City")),
            @AttributeOverride(name = "country", column = @Column(name = "Billing_Country"))
    })

    private Address billingAddress;

    /**
     * Delivering address
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "Delivering_street")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "Delivering_doorNumber")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "Delivering_postalCode")),
            @AttributeOverride(name = "city", column = @Column(name = "Delivering_City")),
            @AttributeOverride(name = "country", column = @Column(name = "Delivering_Country"))
    })

    private Address deliveringAddress;

    /**
     * Email from clerk who created orders
     */
    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "Clerks_Email"))
    private EmailAddress clerkEmail;

    /**
     * Method of communication between sales clerk and respective client
     */
    private String method;

    /**
     * Date when the interaction happened
     */
    @Temporal(TemporalType.DATE)
    private Calendar interactionDate;

    /**
     * Additional comment about the interaction
     */
    private String comment;

    /**
     * Total price before taxes
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amountBeforeTaxes")),
            @AttributeOverride(name = "currency", column = @Column(name = "currencyBeforeTaxes") )

    })
    private Cash priceBeforeTaxes;

    /**
     * Total price after taxes
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amountAfterTaxes") ),
            @AttributeOverride(name = "currency", column = @Column(name = "currencyAfterTaxes") )

    })
    private Cash priceAfterTaxes;

    /**
     * Current status from the orders
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "Payment_Method"))
    private PaymentMethod paymentMethod;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "Shipment_Method"))
    private ShipmentMethod shipmentMethod;

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
     * Constructor used when orders is created by a customer
     *
     * @param products         map of products from shopping cart
     * @param billing          address for billing
     * @param delivering       address for delivering
     * @param paymentMethod    method of payment
     * @param shipmentMethod   method of shipment
     * @param priceBeforeTaxes price without taxes applied
     * @param priceAfterTaxes  price with taxes applied
     */
    public Order(Map<Product, Integer> products, Address billing, Address delivering, PaymentMethod paymentMethod, ShipmentMethod shipmentMethod, Cash priceBeforeTaxes, Cash priceAfterTaxes, Customer customer) {
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
        this.paymentMethod = paymentMethod;
        this.shipmentMethod = shipmentMethod;
        this.status = OrderStatus.PAYMENT_PENDING;
    }

    /**
     * Constructor used when orders is created by a sales clerk on behalf of a given customer
     *
     * @param products         map of products inserted at the time
     * @param billing          address for billing
     * @param delivering       address for delivering
     * @param paymentMethod    method of payment
     * @param shipmentMethod   method of shipment
     * @param priceBeforeTaxes price without taxes applied
     * @param priceAfterTaxes  price with taxes applied
     * @param clerkEmail       email of the sales clerk responsible for the orders creation
     * @param method           method of communication between sales clerk and respective client
     * @param interactionDate  date of the interaction
     * @param comment          additional comment
     */
    public Order(Map<Product, Integer> products, Address billing, Address delivering, PaymentMethod paymentMethod, ShipmentMethod shipmentMethod, Cash priceBeforeTaxes, Cash priceAfterTaxes, EmailAddress clerkEmail, String method, Calendar interactionDate, String comment, Customer customer) {
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
        this.paymentMethod = paymentMethod;
        this.shipmentMethod = shipmentMethod;
        this.status = OrderStatus.PAYMENT_PENDING;
        this.customer = customer;
    }
    /////////////////////////////////////////////////////////////Status Management//////////////////////////////////////////////////////////////


    public OrderStatus status(){
        return this.status;
    }

    public void makePaid(){
        status = OrderStatus.PAID;
    }

    public void isDispatched(){
        status = OrderStatus.DISPATCHED;
    }

    public boolean isPreparePending() {
        return status() == OrderStatus.PREPARE_PENDING;
    }

    public void isPrepared() {
        status = OrderStatus.PREPARED;
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param other orders
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

    @Override
    public String toString() {
        return "\n\n=== Order ===\n" +
                "\nCustomer: " + customer.toStringToOrder() +
                "\nPk: " + pk +
                "\nBilling address: " + billingAddress +
                "\nDelivering address: " + deliveringAddress +
                "\nPrice pos taxes: " + priceAfterTaxes +
                "\nStatus: " + status +
                "\nShipment method: " + shipmentMethod +
                "\n\nOrdered Products=" + orderedProducts +
                "}\n\n";
    }

    /**
     * @return id
     */
    @Override
    public Long identity() {
        return pk;
    }
}
