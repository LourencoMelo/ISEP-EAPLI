package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.framework.actions.Action;

public class ForceOrderOnAGVAction implements Action {

    @Override
    public boolean execute() {
        return new ForceOrderOnAGVUI().show();
    }
}
