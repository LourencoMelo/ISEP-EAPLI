/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.Products.Action.AddProductAction;
import eapli.base.app.backoffice.console.presentation.Products.Action.AddProductCategoryAction;
import eapli.base.app.backoffice.console.presentation.Products.Action.ListProductAction;
import eapli.base.app.backoffice.console.presentation.Products.Action.ListProductsWithFiltersAction;
import eapli.base.app.backoffice.console.presentation.orders.Action.AutomaticAssignmentOrderAction;
import eapli.base.app.backoffice.console.presentation.orders.Action.ChangeOrderToDispatchedAction;
import eapli.base.app.backoffice.console.presentation.orders.Action.CreateOrderForClientAction;
import eapli.base.app.backoffice.console.presentation.registerCustomer.RegisterCustomerAction;
import eapli.base.app.backoffice.console.presentation.warehouseemployee.ConfigureAGVAction;
import eapli.base.app.backoffice.console.presentation.warehouseemployee.ForceOrderOnAGVAction;
import eapli.base.app.backoffice.console.presentation.warehouseemployee.ImportJSONFileAction;
import eapli.base.app.backoffice.console.presentation.warehouseemployee.ShowDashboardAction;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 4;

    //SALES CLERK
    private static final int ADD_PRODUCT = 1;
    private static final int ADD_PRODUCT_CATEGORY = 2;
    private static final int REGISTER_CUSTOMER = 3;
    private static final int LIST_ALL_PRODUCTS = 4;
    private static final int CREATE_ORDER = 5;
    private static final int LIST_FILTERED_PRODUCTS = 6;
    private static final int ASSIGN_ORDER = 7;


    //WAREHOUSE_EMPLOYEE
    private static final int IMPORT_JSONFILE = 1;
    private static final int CONFIGURE_AGV = 2;
    private static final int CHANGE_TO_DISPATCH_ORDER = 3;
    private static final int FORCE_ORDER_ON_AGV = 4;
    private static final int AUTOMATIC_ASSIGNMENT_ORDER = 5;
    private static final int SHOW_DASHBOARD = 6;



    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.SALES_CLERK)) {
            final Menu usersMenu = buildSalesClerkMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.WAREHOUSE_EMPLOYEE)) {
            final Menu userMenu = buildWarehouseEmployee();
            mainMenu.addSubMenu(USERS_OPTION, userMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    /////////////////////////////////////////////////////Specific Menus//////////////////////////////////////////////////////////

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildSalesClerkMenu() {
        final Menu menu = new Menu("Sales Clerk >");

        menu.addItem(ADD_PRODUCT, "Add Product", new AddProductAction());
        menu.addItem(ADD_PRODUCT_CATEGORY, "Add Product Category", new AddProductCategoryAction());
        menu.addItem(REGISTER_CUSTOMER,"Register customer", new RegisterCustomerAction());
        menu.addItem(LIST_ALL_PRODUCTS, "List all Products", new ListProductAction());
        menu.addItem(CREATE_ORDER, "Create a order for a client", new CreateOrderForClientAction());
        menu.addItem(LIST_FILTERED_PRODUCTS, "List products with filters", new ListProductsWithFiltersAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildWarehouseEmployee(){
        final Menu menu = new Menu("Warehouse Employee >");

        menu.addItem(IMPORT_JSONFILE, "Import JSON file", new ImportJSONFileAction());
        menu.addItem(CONFIGURE_AGV, "Configure AGV", new ConfigureAGVAction());
        menu.addItem(CHANGE_TO_DISPATCH_ORDER, "Change to dispatch order", new ChangeOrderToDispatchedAction());
        menu.addItem(FORCE_ORDER_ON_AGV, "Force an Order to an AGV", new ForceOrderOnAGVAction());
        menu.addItem(AUTOMATIC_ASSIGNMENT_ORDER, "Automatically assign orders", new AutomaticAssignmentOrderAction());
        menu.addItem(SHOW_DASHBOARD, "Show Dashboard", new ShowDashboardAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

}
