package eapli.base.app.backoffice.console.presentation.orders.UI;

import eapli.base.ordermanagement.application.AutomaticAssignController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AutomaticAssignmentOrdersUI extends AbstractUI {

    AutomaticAssignController controller = new AutomaticAssignController();

    @Override
    protected boolean doShow() {
        try{
            int option = Console.readInteger("Do you want AgvManager Server to automatically assign orders to agvs? 0->Yes 1->No");

            if (option == 0){
                this.controller.automaticAssignment();
            }

            return false;
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Automatically assign orders";
    }
}
