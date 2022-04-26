package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.ProductCategory;

public class ListProductCategoryController {

    private final ListProductCategoryService lpcs = new ListProductCategoryService();

    public Iterable<ProductCategory> listProductCategories() {
        return lpcs.activeProductCategories();
    }
}
