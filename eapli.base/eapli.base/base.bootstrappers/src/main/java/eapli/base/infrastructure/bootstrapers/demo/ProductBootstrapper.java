package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.customermanagement.application.RegisterCustomerController;
import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.application.CreateOrderForClientController;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.ordermanagement.domain.ShipmentMethod;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.application.ListProductController;
import eapli.base.productmanagement.domain.*;
import eapli.base.productmanagement.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.warehousemanagement.domain.agv.MaxVolume;
import eapli.base.warehousemanagement.domain.agv.MaxWeight;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.TransactionSystemException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ProductBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ProductCategoryBootstrapper.class);

    private final ProductCategoryRepository productCategoryRepository = PersistenceContext.repositories().productCategories();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    private final ListProductController catalogController = new ListProductController();

    final AddProductController productController = new AddProductController();
    final CreateOrderForClientController orderController = new CreateOrderForClientController();
    final RegisterCustomerController registerCustomerController = new RegisterCustomerController();

    private ProductCategory getProductCategory(final String code) {
        for (ProductCategory p : productCategoryRepository.activeProductCategories()) {
            if (p.getCode().equals(AlphanumericCode.valueOf(code))) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean execute() {
        createProducts();

        System.out.println("Product Bootstrap done.");

        return true;
    }

    private void createProducts() {
        final var clothe1 = getProductCategory(TestDataConstants.PRODUCT_CATEGORY_CLOTHE);
        final var beauty2 = getProductCategory(TestDataConstants.PRODUCT_CATEGORY_BEAUTY);
        final var kitchen3 = getProductCategory(TestDataConstants.PRODUCT_CATEGORY_KITCHEN);

        Set<Photo> set1 = new HashSet<>();
        Set<Photo> set2 = new HashSet<>();
        Set<Photo> set3 = new HashSet<>();
        Set<Photo> set4 = new HashSet<>();

        try {
            set1.add(productController.donePhoto(MyFrame.method(new File("photos/casacopele.jpg"))));
            set2.add(productController.donePhoto(MyFrame.method(new File("photos/calcasazuis.jpeg"))));
            set3.add(productController.donePhoto(MyFrame.method(new File("photos/batom.jpg"))));
            set4.add(productController.donePhoto(MyFrame.method(new File("photos/copedepedebarro.jpg"))));
            set4.add(productController.donePhoto(MyFrame.method(new File("photos/copodepedebarro2.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        registerProduct(clothe1, "Casaco", "Casaco de pele", "Casaco castanho de pele"
                , "Casaco castanho de pele tamanho S", "Zara", "111111", 20
                , 24.2, "EAN-13", 5401111111111L, 111111111, set1,30000,0.8,1,1);
       registerProduct(clothe1, "Calças", "Calças de pele", "Calças azuis de pele"
                , "Calças azuis de pele tamanho M", "Tommy Hilfiger", "111112"
                , 40, 48.4, "EAN-13", 5401111111112L
                , 111111112, set2,30000,0.8,2,1);
        registerProduct(beauty2, "Batom", "Batom de cera", "Batom vermelho de cera"
                , "Batom vermelho de cera a prova de agua", "Kiko", "111113"
                , 10, 12.1, "EAN-13", 5401111111113L
                , 111111113, set3,250,0.05,3,1);
        registerProduct(beauty2, "Base", "Base de agua", "Base transparente de agua"
                , "Batom transparente de agua para esconder rugas", "Perfumes&Companhia"
                , "111114", 18, 21.78, "EAN-13", 5401111111114L
                , 111111114, null,250,0.05,1,2);
        registerProduct(kitchen3, "Prato", "Prato de ceramica", "Prato de ceramica quadrado"
                , "Prato de ceramica quadrado com bordas redondas", "Vista Alegre"
                , "111115", 10, 12.1, "EAN-13", 5401111111115L
                , 111111115, null,4500,0.320,2,2);
        registerProduct(kitchen3, "Copo", "Copo de pe", "Copo de pe de barro"
                , "Copo de pe de barro colorido", "Continente"
                , "111116", 3, 3.63, "EAN-13", 5401111111116L
                , 111111116, set4,2500,0.200,3,2);
    }

    private Optional<Product> registerProduct(ProductCategory category, String name, String shortDescription
            , String extendedDescription, String technicalDescription, String brand
            , String reference, double unitaryPreTaxPrice, double unitaryPosTaxPrice, String formatBarCode, long barcode
            , int productionCode, Set<Photo> photos, double volume, double weight,int row,int aisle) {

        try {
            LOGGER.debug("{} ( {} )", name, category);
            return Optional.of(
                    productController.addProduct(category, name, shortDescription, extendedDescription, technicalDescription, brand
                            , reference, unitaryPreTaxPrice, unitaryPosTaxPrice, formatBarCode, barcode, productionCode, photos,volume,weight,row,aisle));
        } catch (final IntegrityViolationException | ConcurrencyException
                | TransactionSystemException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated object
            LOGGER.warn("Assuming {} already exists (see trace log for details on {} {})",
                    name,
                    e.getClass().getSimpleName(), e.getMessage());
            LOGGER.trace(e);
            return Optional.empty();
        }

    }

    }
