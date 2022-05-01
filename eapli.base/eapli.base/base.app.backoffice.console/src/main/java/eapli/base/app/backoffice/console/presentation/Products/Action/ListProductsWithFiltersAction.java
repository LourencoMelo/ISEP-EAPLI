package eapli.base.app.backoffice.console.presentation.Products.Action;

import eapli.base.app.backoffice.console.presentation.Products.UI.ListProductsWithFiltersUI;
import eapli.framework.actions.Action;

public class ListProductsWithFiltersAction implements Action {

    @Override
    public boolean execute() {
        return new ListProductsWithFiltersUI().show();
    }
}
