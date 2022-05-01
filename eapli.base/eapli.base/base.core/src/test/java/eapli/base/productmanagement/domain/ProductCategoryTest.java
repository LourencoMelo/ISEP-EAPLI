package eapli.base.productmanagement.domain;

import eapli.framework.general.domain.model.Description;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductCategoryTest {

    private static final String CLOTHE_PRODUCT_CATEGORY = "CLOTHE1";

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeMustNotBeNull() {
        System.out.println("must have a code");
        new ProductCategory(null, Description.valueOf("Clothe product"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureCodeMustNotBeEmpty() {
        System.out.println("must have a non-empty code");
        new ProductCategory(AlphanumericCode.valueOf(""), Description.valueOf("Clothe product"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionMustNotBeNull() {
        System.out.println("must have a description");
        new ProductCategory(AlphanumericCode.valueOf(CLOTHE_PRODUCT_CATEGORY), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureDescriptionMustNotBeEmpty() {
        System.out.println("must have a non-empty description");
        new ProductCategory(AlphanumericCode.valueOf(CLOTHE_PRODUCT_CATEGORY), Description.valueOf(""));
    }

    @Test
    public void ensureProductCategoryCreatedWitnAnIdentityHasThatIdentity() {
        System.out.println("ensureProductCategoryCreatedWitnAnIdentityHasThatIdentity - identity()");

        final String code = "CLOTHE1";
        final ProductCategory instance = new ProductCategory(AlphanumericCode.valueOf(code), Description.valueOf("Clothe product"));

        assertEquals(code, instance.identity());
    }

    @Test
    public void ensureDishTypeCreatedWitnAnIdentityHasThatIdentity2() {
        System.out.println("ensureDishTypeCreatedWitnAnIdentityHasThatIdentity - hasIdentity");

        final String code = "CLOTHE1";
        final String description = "Clothe product";
        final ProductCategory instance = new ProductCategory(AlphanumericCode.valueOf(code), Description.valueOf(description));

        assertTrue(instance.hasIdentity(code));
    }

}
