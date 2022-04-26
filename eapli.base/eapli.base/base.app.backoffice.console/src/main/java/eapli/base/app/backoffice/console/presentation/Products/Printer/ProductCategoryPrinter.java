package eapli.base.app.backoffice.console.presentation.Products.Printer;

import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.visitor.Visitor;

public class ProductCategoryPrinter implements Visitor<eapli.base.productmanagement.domain.ProductCategory> {

    @Override
    public void visit(ProductCategory visitee) {
        System.out.println(visitee.toString());
    }
}
