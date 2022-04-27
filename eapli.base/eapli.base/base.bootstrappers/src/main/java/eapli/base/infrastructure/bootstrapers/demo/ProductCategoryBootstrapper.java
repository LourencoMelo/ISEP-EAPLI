package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.TestDataConstants;
import eapli.base.productmanagement.application.AddProductCategoryController;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductCategoryBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ProductCategoryBootstrapper.class);


    @Override
    public boolean execute() {
        register(TestDataConstants.PRODUCT_CATEGORY_CLOTHE, "Clothe product");
        register(TestDataConstants.PRODUCT_CATEGORY_BEAUTY, "Beauty product");
        register(TestDataConstants.PRODUCT_CATEGORY_KITCHEN, "Kitchen product");

        return true;
    }

    private void register(final String code, final String description) {
        final AddProductCategoryController controller = new AddProductCategoryController();
        try {
            controller.addProductCategory(code, description);

        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", code);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
