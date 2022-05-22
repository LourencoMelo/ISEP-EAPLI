package eapli.base.app.backoffice.console.presentation.orders.Action;

import eapli.base.app.backoffice.console.presentation.orders.UI.ChangeOrderToDispatchedUI;
import eapli.framework.actions.Action;

public class ChangeOrderToDispatchedAction implements Action {

    @Override
    public boolean execute() {
        return new ChangeOrderToDispatchedUI().show();
    }
}
