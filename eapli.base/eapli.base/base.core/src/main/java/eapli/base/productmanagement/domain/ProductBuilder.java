package eapli.base.productmanagement.domain;

import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.CustomerBuilder;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import eapli.framework.validations.Preconditions;

import java.util.Set;

public class ProductBuilder implements DomainFactory<Product> {

    private Product theProduct;

    private Designation name;
    private ProductCategory productCategory;
    private Description shortDescription;
    private Description extendedDescription;
    private Description technicalDescription;
    private Designation brand;
    private Reference reference;
    private Cash pricePreTax;
    private Cash pricePosTax;
    private BarCode barCode;
    private int productionCode;
    private Set<Photo> photosCollection;

    public ProductBuilder ofType(ProductCategory productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public ProductBuilder named(String name) {
        return named(Designation.valueOf(name));
    }

    public ProductBuilder named(final Designation name) {
        this.name = name;
        return this;
    }

    public ProductBuilder shortDescriptioned(String shortDescription) {
        return shortDescriptioned(Description.valueOf(shortDescription));
    }

    public ProductBuilder shortDescriptioned(Description shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public ProductBuilder extendedDescriptioned(String extendedDescription) {
        return extendedDescriptioned(Description.valueOf(extendedDescription));
    }

    public ProductBuilder extendedDescriptioned(Description extendedDescription) {
        this.extendedDescription = extendedDescription;
        return this;
    }

    public ProductBuilder technicalDescriptioned(String technicalDescription) {
        return technicalDescriptioned(Description.valueOf(technicalDescription));
    }

    public ProductBuilder technicalDescriptioned(Description technicalDescription) {
        this.technicalDescription = technicalDescription;
        return this;
    }

    public ProductBuilder branded(String brand) {
        return branded(Designation.valueOf(brand));
    }

    public ProductBuilder branded(Designation brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder referenced(String reference) {
        return referenced(Reference.valueOf(reference));
    }

    public ProductBuilder referenced(Reference reference) {
        this.reference = reference;
        return this;
    }

    public ProductBuilder preTaxcosting(final Cash price) {
        this.pricePreTax = price;
        return this;
    }

    public ProductBuilder posTaxcosting(final Cash price) {
        this.pricePosTax = price;
        return this;
    }

    public ProductBuilder makingBarcode(String format, long code) {
        this.barCode = new BarCode(format, code);
        return this;
    }

    public ProductBuilder makingProductionCode(int productionCode) {
        this.productionCode = productionCode;
        return this;
    }



    public Photo donePhoto(byte[] photo){
        return new Photo(photo);
    }

    public ProductBuilder withPhotos(final Set<Photo> photos) {
        // we will simply ignore if we receive a null set
        if (photos != null) {
            photos.forEach(this::withPhotos);
        }
        return this;
    }

    public ProductBuilder withPhotos(final Photo photo) {
        buildOrThrow();
        theProduct.addPhotos(photo);
        return this;
    }

    private Product buildOrThrow() {
        try {
            if (theProduct != null) {
                return theProduct;
            }
            Preconditions.noneNull(name, shortDescription, extendedDescription, technicalDescription, brand, reference, pricePreTax, pricePosTax, barCode, productionCode);
            theProduct = new Product(productCategory, name, shortDescription, extendedDescription, technicalDescription, brand, reference, pricePreTax, pricePosTax, barCode, productionCode);
            return theProduct;
        }catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public Product build() {
        final Product ret =buildOrThrow();
        theProduct = null;
        return ret;
    }


}
