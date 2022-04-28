package eapli.base.app.backoffice.console.presentation.orders.Action;

import eapli.base.app.backoffice.console.presentation.orders.UI.CreateOrderUI;
import eapli.framework.actions.Action;

public class CreateOrderForClientAction implements Action {

    @Override
    public boolean execute() {
        return new CreateOrderUI().show();
    }
}
