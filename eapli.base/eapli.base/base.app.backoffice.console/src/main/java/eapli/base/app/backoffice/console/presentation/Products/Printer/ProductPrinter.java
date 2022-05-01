package eapli.base.app.backoffice.console.presentation.Products.Printer;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.visitor.Visitor;

import java.io.IOException;

public class ProductPrinter implements Visitor<Product> {

    @Override
    public void visit(final Product visitee) {
        String superCategoryToWrite = "";
        if(visitee.getCategory().getSuperCategory()!=null) {
            superCategoryToWrite = visitee.getCategory().getSuperCategory().getCode().toString();
        }
        System.out.printf("%-30s%-25s%-25s%-10s%-20s%-20s", visitee.getName(),visitee.getCategory(),superCategoryToWrite,visitee.getPosPrice(),visitee.getBrand(),visitee.getInternalCode());
        try {
            visitee.printSetPhotos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
