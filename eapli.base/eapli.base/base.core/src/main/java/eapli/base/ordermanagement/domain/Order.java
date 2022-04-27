package eapli.base.ordermanagement.domain;

import eapli.base.costumermanagement.domain.Address;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.general.domain.model.Money;

import javax.persistence.*;
import java.util.*;

@Entity
public class Order {//implements AggregateRoot<Designation> {

    /**
     * Numeric identifier
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Date of the registration
     */
    private Calendar registDate;

    /**
     * Billing address
     */
    private Address billingAddress;

    /**
     * Delivering address
     */
    private Address deliveringAddress;

    /**
     * Ordered products with their respective quantities
     */
    @ElementCollection
    private Map<Product, Integer> orderedProducts;

    @Embedded
    private EmailAddress clerkEmail;

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

    public EmailAddress getClerkEmail() {
        return clerkEmail;
    }

    public void setClerkEmail(EmailAddress clerkEmail) {
        this.clerkEmail = clerkEmail;
    }

    public Money getPriceAfterTaxes() {
        return priceAfterTaxes;
    }

    public Money getPriceBeforeTaxes() {
        return priceBeforeTaxes;
    }

    protected Order() {
        //Empty
    }

    public Order(Map<Product, Integer> products, Address billing, Address delivering, EmailAddress clerkEmail){
        this.orderedProducts = products;
        this.billingAddress = billing;
        this.deliveringAddress = delivering;
        this.clerkEmail = clerkEmail;
        this.registDate = Calendar.getInstance();
    }

//    @Override
//    public boolean sameAs(Object other) {
//        return false;
//    }

//    @Override
//    public Object identity() {
//        return null;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        return 0;
//    }
}
