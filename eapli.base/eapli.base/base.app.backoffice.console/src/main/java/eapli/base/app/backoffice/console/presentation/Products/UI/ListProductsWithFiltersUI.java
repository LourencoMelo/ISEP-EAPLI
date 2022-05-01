package eapli.base.app.backoffice.console.presentation.Products.UI;

import eapli.base.app.backoffice.console.presentation.Products.Printer.ProductCategoryPrinter;
import eapli.base.productmanagement.application.ListProductController;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;

public class ListProductsWithFiltersUI {

    ListProductController listProductController = new ListProductController();

    public boolean show() {
        int select = Console.readInteger("Which filter do you want to use ? \n 0 -> Category \n 1 -> Brand \n 2 -> Description");

        switch (select) {
            case 0:
                final Iterable<ProductCategory> productCategories = this.listProductController.productCategories();

                final SelectWidget<ProductCategory> selector = new SelectWidget<>("Product Categories:", productCategories,
                        new ProductCategoryPrinter());
                selector.show();
                final ProductCategory theProductCategory = selector.selectedElement();
                System.out.printf("%-30s%-25s%-25s%-20s%-20s", "Name", "Category", "Price With Taxes", "Brand", "Internal Code");
                System.out.println("\n");
                for (Product s : listProductController.findByCategory(theProductCategory)) {
                    System.out.printf("%-30s%-25s%-25s%-20s%-20s", s.getName(),s.getCategory(),s.getPosPrice(),s.getBrand(),s.getInternalCode());
                    System.out.println("\n \n");
                }
                break;
            case 1:
                String brand = Console.readLine("Brand : ");
                System.out.printf("%-30s%-25s%-25s%-20s%-20s", "Name", "Category", "Price With Taxes", "Brand", "Internal Code");
                System.out.println("\n");
                for (Product s : listProductController.findByBrand(Designation.valueOf(brand))) {
                    System.out.printf("%-30s%-25s%-25s%-20s%-20s", s.getName(),s.getCategory(),s.getPosPrice(),s.getBrand(),s.getInternalCode());
                    System.out.println("\n \n");
                }
                break;
            case 2:
                String designation = Console.readLine("Short Description : ");
                System.out.printf("%-30s%-25s%-25s%-20s%-20s", "Name", "Category", "Price With Taxes", "Brand", "Short Description");
                System.out.println("\n");
                for (Product s : listProductController.findByDescription(Description.valueOf(designation))) {
                    System.out.printf("%-30s%-25s%-25s%-20s%-20s", s.getName(),s.getCategory(),s.getPosPrice(),s.getBrand(),s.getShortDescription());
                    System.out.println("\n \n");
                }
                break;
            default:
                System.out.println("Command does not exist");
                break;
        }

        return false;
    }
}
