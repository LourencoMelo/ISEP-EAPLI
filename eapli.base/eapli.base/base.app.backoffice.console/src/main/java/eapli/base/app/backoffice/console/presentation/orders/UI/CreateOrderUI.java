package eapli.base.app.backoffice.console.presentation.orders.UI;

import eapli.base.app.backoffice.console.presentation.Products.Printer.ProductPrinter;
import eapli.base.app.backoffice.console.presentation.orders.Printer.CustomerPrinter;
import eapli.base.app.backoffice.console.presentation.registerCustomer.RegisterCustomerAction;
import eapli.base.customermanagement.application.ListCustomersController;
import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.ordermanagement.application.CreateOrderForClientController;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.ordermanagement.domain.ShipmentMethod;
import eapli.base.productmanagement.application.ListProductController;
import eapli.base.productmanagement.domain.Cash;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CreateOrderUI extends AbstractUI {

    private final CreateOrderForClientController controller = new CreateOrderForClientController();
    private final ListProductController catalogController = new ListProductController();

    private final ListCustomersController customersController = new ListCustomersController();

    @Override
    protected boolean doShow() {

        try {

            String clientRegistAnswer = Console.readLine("Is the client already registered in the system? (Yes or No)\n");

            if (clientRegistAnswer.equalsIgnoreCase("no")) { //If the client isn't registered in the system
                new RegisterCustomerAction().execute();
            } else if (!clientRegistAnswer.equalsIgnoreCase("yes")) {
                throw new IOException("Invalid answer");
            }

            final Iterable<Customer> customers = this.customersController.allCustomers();

            final SelectWidget<Customer> customerSelector = new SelectWidget<>("Customers:", customers, new CustomerPrinter());

            customerSelector.show();

            Customer customer = customerSelector.selectedElement();

            int input = 0;

            Map<Product, Integer> products = new HashMap<>();

            do {

                String idOrCatalogAnswer = Console.readLine("Do you want to search the product for id or look up at the full catalog? (id or catalog) \n");
                if (idOrCatalogAnswer.equalsIgnoreCase("id")) { //If the sales clerk pretend to search the product by id
                    Long id = Console.readLong("Insert the product id : \n");

                    Optional<Product> product = catalogController.findById(id);

                    final int quantity = Console.readInteger("Insert the quantity pretended: \n");

                    if (product.isPresent() && products.containsKey(product.get())) {
                        int aux = products.get(product.get());
                        aux += quantity;
                        products.put(product.get(), aux);
                    } else product.ifPresent(value -> products.put(value, quantity));
                    input = Console.readInteger("Do you want to add more products to the order? Yes -> 0\nNo -> 1 \n");

                } else if (idOrCatalogAnswer.equalsIgnoreCase("catalog")) { //If the sales clerk wants to search the product on the catalog

                    System.out.println("Here is the catalog : \n");

                    final Iterable<Product> catalog = this.catalogController.activeProducts();

                    final SelectWidget<Product> selector = new SelectWidget<>("Products:", catalog, new ProductPrinter());

                    selector.show();

                    final Product product = selector.selectedElement();

                    final int quantity = Console.readInteger("Insert the quantity pretended: \n");

                    if (products.containsKey(product)) {
                        int aux = products.get(product);
                        aux += quantity;
                        products.put(product, aux);
                    } else {
                        products.put(product, quantity);
                    }
                    input = Console.readInteger("Do you want to add more products to the order? Yes -> 0\nNo -> 1 \n");

                } else {
                    throw new IOException("Invalid answer");
                }


            } while (input == 0);

            System.out.println("Insert the billing address : \n");

            String streetName = Console.readLine("Street name: ");
            int doorNumber = Console.readInteger("Door number: ");
            String zipCode = Console.readLine("Zip code: ");
            String city = Console.readLine("City: ");
            String country = Console.readLine("Country: ");

            Address billing = controller.createAddress(streetName, doorNumber, zipCode, city, country);

            System.out.println("Insert the delivering address : \n");

            streetName = Console.readLine("Street name: ");
            doorNumber = Console.readInteger("Door number: ");
            zipCode = Console.readLine("Zip code: ");
            city = Console.readLine("City: ");
            country = Console.readLine("Country: ");

            Address delivering = controller.createAddress(streetName, doorNumber, zipCode, city, country);

            PaymentMethod paymentMethod = new PaymentMethod("Paypal");

            ShipmentMethod shipmentMethod = new ShipmentMethod("Ford Transit", Cash.euros(40.0));

            final String method = Console.readLine("How did you communicated with the client?");

            System.out.println("Insert the date when the communication happened: \n");

            final int day = Console.readInteger("Day: ");

            final int month = Console.readInteger("\nMonth: ");

            final int year = Console.readInteger("\nYear: ");

            Calendar dateEncounter = controller.createCalendarDate(day, month, year);

            int option = Console.readInteger("Do you want to add an additional comment? Yes -> 0\nNo -> 1 \n");

            String comment = "";

            if (option == 0) {
                comment = Console.readLine("Insert the additional comment : \n");
            }

            if (customer != null) {
                Order order = controller.registerOrder(products, billing, delivering, paymentMethod, shipmentMethod, method, dateEncounter, comment, customer);
                customer.addOrder(order);
            } else {
                throw new Exception("Customer needed!");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }


        return false;
    }

    @Override
    public String headline() {
        return "Create Order for Client";
    }
}
