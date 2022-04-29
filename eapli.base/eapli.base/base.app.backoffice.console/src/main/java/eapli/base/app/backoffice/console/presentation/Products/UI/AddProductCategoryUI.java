package eapli.base.app.backoffice.console.presentation.Products.UI;

import eapli.base.productmanagement.application.AddProductCategoryController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddProductCategoryUI extends AbstractUI {


    private final AddProductCategoryController theController = new AddProductCategoryController();

    @Override
    protected boolean doShow() {
        final String code = Console.readLine("Product Category Code:");
        final String description = Console.readLine("Product Category Description:");

        try {
            this.theController.addProductCategory(code, description);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("That code is already in use.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Add Product Category";
    }
}
