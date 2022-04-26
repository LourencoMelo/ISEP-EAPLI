package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class ListProductController {

    private final ListProductService svc = new ListProductService();

    public Iterable<Product> allProducts() {
        return svc.allProducts();
    }
}
