package eapli.base.app.backoffice.console.presentation.orders.Action;

import eapli.base.app.backoffice.console.presentation.orders.UI.ListCustomersUI;
import eapli.framework.actions.Action;

public class ListCustomersAction implements Action {
    @Override
    public boolean execute() {
        return new ListCustomersUI().show();
    }
}
