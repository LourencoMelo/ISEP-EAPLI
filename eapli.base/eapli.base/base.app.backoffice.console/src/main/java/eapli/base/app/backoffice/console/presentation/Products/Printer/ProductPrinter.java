package eapli.base.app.backoffice.console.presentation.Products.Printer;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.visitor.Visitor;

public class ProductPrinter implements Visitor<Product> {

    @Override
    public void visit(final Product visitee) {
        System.out.printf("%-30s%-25s%-10s%-20s%-20s", visitee.getName(),visitee.getCategory(),visitee.getPosPrice(),visitee.getBrand(),visitee.getInternalCode());
    }
}
