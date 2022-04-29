package eapli.base.app.backoffice.console.presentation.Products.Action;

import eapli.base.app.backoffice.console.presentation.Products.UI.AddProductCategoryUI;
import eapli.framework.actions.Action;

public class AddProductCategoryAction implements Action {

    @Override
    public boolean execute() {
        return new AddProductCategoryUI().show();
    }

}
