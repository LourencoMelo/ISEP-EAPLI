package eapli.base.app.backoffice.console.presentation.Products.UI;

import eapli.base.app.backoffice.console.presentation.Products.Printer.ProductCategoryPrinter;
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
        final Iterable<ProductCategory> productCategories = this.theController.productCategories();

        final SelectWidget<ProductCategory> selector = new SelectWidget<>("Product Categories:", productCategories,
                new ProductCategoryPrinter());
        selector.show();
        final ProductCategory theProductCategory = selector.selectedElement();

        final String name = Console.readLine("Name: ");
        final String shortDescription = Console.readLine("Short description: ");
        final String extendedDescription = Console.readLine("Extended description: ");
        final String techincalDescription = Console.readLine("Technical description: ");
        final String brand = Console.readLine("Brand: ");
        final String reference = Console.readLine("Reference: ");
        final double preTaxPrice = Console.readDouble("Price before taxes: ");
        final double posTaxPrice = Console.readDouble("Price after taxes: ");

        try {
            this.theController.addProduct(theProductCategory,name,shortDescription,extendedDescription
                    , techincalDescription,brand,reference,preTaxPrice,posTaxPrice);
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
