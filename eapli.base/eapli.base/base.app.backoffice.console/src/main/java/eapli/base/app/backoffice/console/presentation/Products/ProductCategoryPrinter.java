package eapli.base.app.backoffice.console.presentation.Products;

import eapli.framework.visitor.Visitor;

public class ProductCategoryPrinter implements Visitor<eapli.base.productmanagement.domain.ProductCategory> {

    @Override
    public void visit(eapli.base.productmanagement.domain.ProductCategory visitee) {
        System.out.printf("%-10s%-30s%-4s", visitee.identity(), visitee.description(),
                String.valueOf(visitee.isActive()));
    }
}
