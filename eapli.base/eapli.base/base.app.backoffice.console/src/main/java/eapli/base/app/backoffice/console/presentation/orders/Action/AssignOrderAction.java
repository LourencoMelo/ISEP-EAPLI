package eapli.base.app.backoffice.console.presentation.orders.Action;

import eapli.base.app.backoffice.console.presentation.orders.UI.AssignOrderUI;
import eapli.framework.actions.Action;

public class AssignOrderAction implements Action {
    @Override
    public boolean execute() {
        return new AssignOrderUI().show();
    }
}
