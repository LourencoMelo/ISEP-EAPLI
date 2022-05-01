package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductBuilderTest {

    private static final ProductCategory CLOTHE_PRODUCT_CATEGORY = new ProductCategory(AlphanumericCode.valueOf("CLOTHE1"), Description.valueOf("Clothe product"));
    private static final String CASACO_NAME = "Casaco";
    private static final String CASACO_SHORT_DESCRIPTION ="Casaco de pele";
    private static final String CASACO_EXTENDED_DESCRIPTION = "Casaco castanho de pele";
    private static final String CASACO_TECHNICAL_DESCRIPTION = "Casaco castanho de pele tamanho S";
    private static final String CASACO_BRAND = "Zara";
    private static final String CASACO_REFERENCE = "111111";
    private static final double CASACO_UNITARY_PRE_TAX_PRICE = 20;
    private static final double CASACO_UNITARY_POS_TAX_PRICE = 24.2;
    private static final String CASACO_FORMAT_BARCODE = "EAN-13";
    private static final long CASACO_BARCODE = 5401111111112L;
    private static final int CASACO_PRODUCTION_CODE = 111111112;
    private static final Set<Photo> CASACO_PHOTOS_COLLECTION = new HashSet<>();

    private Product buildProduct() {
        return new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
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
    public void ensureCanBuildWithNullPhotos() {
        final Product product = buildProduct();
        assertNotNull(product);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullProductCategory() {
        new ProductBuilder().ofType(null)
                .named(Designation.valueOf(CASACO_NAME))
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

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullProductCategory2() {
        new ProductBuilder().named(Designation.valueOf(CASACO_NAME))
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

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildWithNullName() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(null))
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

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullName2() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
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

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildWithNullReference() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced((String) null)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildWithNullReference2() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(null))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullPricePreTax() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .preTaxcosting(null)
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullPricePreTax2() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullPricePosTax() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(null)
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCannotBuildWithNullBarCode() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(null, 0)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullBarCode2() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(CASACO_PRODUCTION_CODE)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullProductionCode() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .makingProductionCode(0)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void ensureCannotBuildWithNullProductionCode2() {
        new ProductBuilder().ofType(CLOTHE_PRODUCT_CATEGORY)
                .named(Designation.valueOf(CASACO_NAME))
                .shortDescriptioned(CASACO_SHORT_DESCRIPTION)
                .extendedDescriptioned(CASACO_EXTENDED_DESCRIPTION)
                .technicalDescriptioned(CASACO_TECHNICAL_DESCRIPTION)
                .branded(CASACO_BRAND)
                .referenced(CASACO_REFERENCE)
                .preTaxcosting(Cash.euros(CASACO_UNITARY_PRE_TAX_PRICE))
                .posTaxcosting(Cash.euros(CASACO_UNITARY_POS_TAX_PRICE))
                .makingBarcode(CASACO_FORMAT_BARCODE, CASACO_BARCODE)
                .withPhotos(CASACO_PHOTOS_COLLECTION)
                .build();
    }

}
