package eapli.base.app.backoffice.console.presentation.warehouseemployee;

import eapli.framework.actions.Action;

public class ImportJSONFileAction implements Action{
    @Override
    public boolean execute() {
        return new ImportJSONFileUI().show();
    }
}
