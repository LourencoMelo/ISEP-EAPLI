package eapli.base.app.backoffice.console.presentation.Products.Action;

import eapli.base.app.backoffice.console.presentation.Products.UI.ListProductCategoryUI;
import eapli.framework.actions.Action;

public class ListProductCategoryAction implements Action {

    @Override
    public boolean execute() {
        return new ListProductCategoryUI().show();
    }

}
