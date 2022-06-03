package eapli.base.app.backoffice.console.presentation.orders.Action;

import eapli.base.app.backoffice.console.presentation.orders.UI.ChangeOrderToBeingDeliveredUI;
import eapli.framework.actions.Action;

public class ChangerOrderToBeingDeliveredAction implements Action {

    @Override
    public boolean execute() {
        return new ChangeOrderToBeingDeliveredUI().show();
    }

}
