package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.app.backoffice.console.presentation.Products.ProductCategoryPrinter;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AddProductUI extends AbstractUI {

    private final AddProductController theController = new AddProductController();

    @Override
    protected boolean doShow() {
        final Iterable<eapli.base.productmanagement.domain.ProductCategory> productCategories = this.theController.productCategories();

        final SelectWidget<eapli.base.productmanagement.domain.ProductCategory> selector = new SelectWidget<>("Product Categories:", productCategories,
                new ProductCategoryPrinter());
        selector.show();
        final ProductCategory productCategory = selector.selectedElement();

        final String name = Console.readLine("Name: ");
        final String shortDescription = Console.readLine("Short Description: ");
        final String extendedDescription = Console.readLine("ExtendedDescription: ");
        final String technicalDescription = Console.readLine("Technical Description: ");
        final String brand = Console.readLine("Brand: ");
        final String reference = Console.readLine("Reference: ");
        final double unitaryPreTaxPrice = Console.readDouble("Unitary price befores taxes ");
        final double unitaryPosTaxPrice = Console.readDouble("Unitary price after taxes: ");

        try {
            this.theController.addProduct(productCategory, name, shortDescription, extendedDescription, technicalDescription
                    , brand, reference, unitaryPreTaxPrice, unitaryPosTaxPrice);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a product which already exists in the database.");
        }

        return false;

    }

    @Override
    public String headline() {
        return "Add Product";
    }
}
