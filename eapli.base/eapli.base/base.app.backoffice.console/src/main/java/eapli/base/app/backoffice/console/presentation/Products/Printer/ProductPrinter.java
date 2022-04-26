package eapli.base.app.backoffice.console.presentation.Products.Printer;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.visitor.Visitor;

public class ProductPrinter implements Visitor<Product> {

    @Override
    public void visit(final Product visitee) {
        System.out.println(visitee.toString());
    }
}
