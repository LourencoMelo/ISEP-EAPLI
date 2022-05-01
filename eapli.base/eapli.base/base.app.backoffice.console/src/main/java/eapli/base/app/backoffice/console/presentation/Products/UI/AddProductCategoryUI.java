package eapli.base.app.backoffice.console.presentation.Products.UI;

import eapli.base.app.backoffice.console.presentation.Products.Printer.ProductCategoryPrinter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.AddProductCategoryController;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AddProductCategoryUI extends AbstractUI {


    private final AddProductCategoryController theController = new AddProductCategoryController();
    private final ProductCategoryRepository repository = PersistenceContext.repositories().productCategories();

    @Override
    protected boolean doShow() {

        final String code = Console.readLine("Product Category Code:");
        final String description = Console.readLine("Product Category Description:");

        int select = Console.readInteger("Do you want a super category? \n 0 -> Yes \n 1 -> No");
        switch (select) {
            case 0:
                final Iterable<ProductCategory> productCategories = repository.activeProductCategories();

                final SelectWidget<ProductCategory> selector = new SelectWidget<>("Product Categories:", productCategories,
                        new ProductCategoryPrinter());
                selector.show();
                final ProductCategory theProductCategory = selector.selectedElement();
                try {
                    this.theController.addProductCategory(code, description, theProductCategory);
                } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                    System.out.println("That code is already in use.");
                }
                break;
            case 1:
                System.out.println("OK!");
                break;
            default:
                System.out.println("Command does not exist");
                break;
        }

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
