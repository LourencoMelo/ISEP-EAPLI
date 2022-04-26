package eapli.base.ordermanagement.domain;

import eapli.base.costumermanagement.domain.Address;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import lombok.Getter;

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
    private Map<Product, Integer> orderedProducts = new HashMap<>();

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

    public Money getPriceAfterTaxes() {
        return priceAfterTaxes;
    }

    public Money getPriceBeforeTaxes() {
        return priceBeforeTaxes;
    }

    protected Order() {
        //Empty
    }

    public Order(Address billing, Address delivering){
        this.billingAddress = billing;
        this.deliveringAddress = delivering;
        this.registDate = Calendar.getInstance();
    }

    /**
     * Adds product to order
     * @param product product to add
     * @param quantity quantity of the product
     */
    public void addProductTo(Product product, Integer quantity){
        this.orderedProducts.put(product, quantity);
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
