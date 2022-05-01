package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import org.junit.Test;

import java.sql.Ref;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class ProductTest {

    private static final ProductCategory CLOTHE_PRODUCT_CATEGORY = new ProductCategory(AlphanumericCode.valueOf("CLOTHE1"), Description.valueOf("Clothe product"));
    private static final Designation CASACO_NAME = Designation.valueOf("Casaco");
    private static final Description CASACO_SHORT_DESCRIPTION = Description.valueOf("Casaco de pele");
    private static final Description CASACO_EXTENDED_DESCRIPTION = Description.valueOf("Casaco castanho de pele");
    private static final Description CASACO_TECHNICAL_DESCRIPTION = Description.valueOf("Casaco castanho de pele tamanho S");
    private static final Designation CASACO_BRAND = Designation.valueOf("Zara");
    private static final Reference CASACO_REFERENCE = Reference.valueOf("111111");
    private static final double CASACO_UNITARY_PRE_TAX_PRICE = 20;
    private static final Cash CASACO_UNITARY_PRE_TAX_PRICE_CASH = Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE);
    private static final double CASACO_UNITARY_POS_TAX_PRICE = 24.2;
    private static final Cash CASACO_UNITARY_POS_TAX_PRICE_CASH = Cash.euros(CASACO_UNITARY_POS_TAX_PRICE);
    private static final String CASACO_FORMAT_BARCODE = "EAN-13";
    private static final long CASACO_BARCODE = 5401111111112L;
    private static final int CASACO_PRODUCTION_CODE = 111111112;
    private static final BarCode CASACO_TOTAL_BARCODE = new BarCode(CASACO_FORMAT_BARCODE,CASACO_BARCODE);
    private static final Set<Photo> CASACO_PHOTOS_COLLECTION = new HashSet<>();

    private Product buildProduct() {
        return new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(CASACO_NAME)
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test
    public void ensureProduct() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveProductCategory() throws Exception {
        new Product(null,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveShortDesignation() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,null,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveExtendedDesignation() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,null,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveTechnicalDesignation() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                null,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveBrand() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,null,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveReference() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,null,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHavePreTaxPrice() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,null
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHavePosTaxPrice() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,null,CASACO_TOTAL_BARCODE,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveBarCode() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,null,CASACO_PRODUCTION_CODE
                ,CASACO_PHOTOS_COLLECTION);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveProductionCode() throws Exception {
        new Product(CLOTHE_PRODUCT_CATEGORY,CASACO_NAME,CASACO_SHORT_DESCRIPTION,CASACO_EXTENDED_DESCRIPTION,
                CASACO_TECHNICAL_DESCRIPTION,CASACO_BRAND,CASACO_REFERENCE,CASACO_UNITARY_PRE_TAX_PRICE_CASH
                ,CASACO_UNITARY_POS_TAX_PRICE_CASH,CASACO_TOTAL_BARCODE,0
                ,CASACO_PHOTOS_COLLECTION);
        assertTrue(true);
    }

}
