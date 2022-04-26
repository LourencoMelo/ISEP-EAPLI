package eapli.base.app.backoffice.console.presentation.Products.Action;

import eapli.base.app.backoffice.console.presentation.Products.UI.AddProductUI;
import eapli.framework.actions.Action;

public class AddProductAction implements Action {

    @Override
    public boolean execute() {
        return new AddProductUI().show();
    }

}
