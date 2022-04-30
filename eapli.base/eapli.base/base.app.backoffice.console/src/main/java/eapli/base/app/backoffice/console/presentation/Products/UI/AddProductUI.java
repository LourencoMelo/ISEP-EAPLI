package eapli.base.app.backoffice.console.presentation.Products.UI;

import eapli.base.app.backoffice.console.presentation.Products.Printer.ProductCategoryPrinter;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.domain.MyFrame;
import eapli.base.productmanagement.domain.Photo;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import javassist.bytecode.ByteArray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class AddProductUI extends AbstractUI {

    private final AddProductController theController = new AddProductController();

    @Override
    protected boolean doShow() {
        final Iterable<ProductCategory> productCategories = this.theController.productCategories();

        final SelectWidget<ProductCategory> selector = new SelectWidget<>("Product Categories:", productCategories,
                new ProductCategoryPrinter());
        selector.show();
        final ProductCategory theProductCategory = selector.selectedElement();

        final String name = Console.readLine("Name: ");
        final String shortDescription = Console.readLine("Short description: ");
        final String extendedDescription = Console.readLine("Extended description: ");
        final String techincalDescription = Console.readLine("Technical description: ");
        final String brand = Console.readLine("Brand: ");
        final String reference = Console.readLine("Reference (6 digits): ");
        final double preTaxPrice = Console.readDouble("Price before taxes: ");
        final double posTaxPrice = Console.readDouble("Price after taxes: ");
        final String formatBarcdoe = Console.readLine("BarCode format (example: EAN-13):");
        final long barcode = Console.readLong("Barcode (13 digits): ");
        final int productionCode = Console.readInteger("Produciton code (9 digits): ");

        Set<Photo> photos = new HashSet<>();
        try {
            photos = fillPhotos();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.theController.addProduct(theProductCategory,name,shortDescription,extendedDescription
                    , techincalDescription,brand,reference,preTaxPrice,posTaxPrice,formatBarcdoe,barcode,productionCode
                    , photos);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a product which already exists in the database.");
        }

        return false;

    }

    private Set<Photo> fillPhotos() throws IOException {
        Set<Photo> photos = new HashSet<>();
        int select;
        do {
            select = Console.readInteger("Do you want to add a photo to the product ? \n 0 -> YES \n 1 -> NO");
            switch (select) {
                case 0:
                    JFileChooser jFileChooser = new JFileChooser();

                    int answer = jFileChooser.showOpenDialog(new JDialog());
                    File file = new File("");

                    if(answer == JFileChooser.APPROVE_OPTION) {
                        file = jFileChooser.getSelectedFile();
                        JOptionPane.showMessageDialog(null, "Image selected successfully!");
                    } else if (answer == JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(null, "Canceled!");
                    }
                    photos.add(theController.donePhoto(MyFrame.method(file)));
                    break;
                case 1:
                    System.out.println("OK!");
                    break;
                default:
                    System.out.println("Command does not exist");
                    break;
            }
        }while(select == 0);
        return photos;
    }

    @Override
    public String headline() {
        return "Add Product";
    }

}
