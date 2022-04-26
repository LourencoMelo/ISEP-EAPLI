package eapli.base.productmanagement.application.viadto;

import eapli.base.productmanagement.dto.ProductDTO;

public class ListProductDTOController {

    private final ListProductDTOService svc = new ListProductDTOService();

    public Iterable<ProductDTO> allProducts() {
        return svc.allProducts();
    }

}
