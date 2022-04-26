package eapli.base.app.backoffice.console.presentation.Products.UI;

import eapli.base.app.backoffice.console.presentation.Products.Printer.ProductCategoryPrinter;
import eapli.base.productmanagement.application.ListProductCategoryController;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListProductCategoryUI extends AbstractListUI<ProductCategory> {

    private final ListProductCategoryController theController = new ListProductCategoryController();

    @Override
    protected Iterable<ProductCategory> elements() {
        return this.theController.listProductCategories();
    }

    @Override
    protected Visitor<ProductCategory> elementPrinter() {
        return new ProductCategoryPrinter();
    }

    @Override
    protected String elementName() {
        return "Product Category";
    }

    @Override
    protected String listHeader() {
        return "PRODUCT CATEGORY";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    public String headline() {
        return "List product categories";
    }
}
