package eapli.base.app.backoffice.console.presentation.orders.Action;

import eapli.base.app.backoffice.console.presentation.orders.UI.AutomaticAssignmentOrdersUI;
import eapli.framework.actions.Action;

public class AutomaticAssignmentOrderAction implements Action {
    @Override
    public boolean execute() {
        return new AutomaticAssignmentOrdersUI().show();
    }
}
