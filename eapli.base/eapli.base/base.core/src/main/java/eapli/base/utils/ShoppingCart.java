package eapli.base.utils;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.general.domain.model.Money;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    /**
     * Map containing all items and their respective quantities
     */
    private Map<Product, Integer> items;

    /**
     * Initializes hash map
     */
    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    /**
     * Adds new product to shopping cart
     *
     * @param product  product to add
     * @param quantity quantity of the product
     */
    public void addToShoppingCart(Product product, Integer quantity) {
        this.items.put(product, quantity);
    }

    /**
     * Removes item from shopping cart. If the quantity to remove is equal or greater tho the one already existent the product is removed from the car. Otherwise, only the quantity is decremented
     * @param product product to remove
     * @param quantity quantity to remove
     */
    public void removeFromShoppingCart(Product product, Integer quantity) {
        if (this.items.get(product) <= quantity) {
            this.items.remove(product);
        } else {
            Integer current_value = this.items.get(product);
            this.items.put(product, current_value - quantity);
        }
    }

    /**
     * Calculates total amount of money before taxes
     * @return total amount of money from the shopping cart
     */
    public Money getTotalAmountWithoutTaxes(){
        Money totalAmount = Money.euros(0.0);
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            double price = entry.getKey().currentPrice().amountAsDouble() * entry.getValue();
            totalAmount.add(Money.euros(price));
        }

        return totalAmount;
    }

    /**
     * Calculates total amount of money after taxes
     * @return total amounte of money from the shopping cart with taxes
     */
    public Money getTotalAmountWithTaxes(){
        return null;
    }

    /**
     * Prints shopping cart information
     * @return shopping cart content
     */
    public String showCart(){
        return items.toString();
    }

    /**
     * Clears all products from shopping cart
     */
    public void clearShoppingCart(){
        this.items.clear();
    }

}
