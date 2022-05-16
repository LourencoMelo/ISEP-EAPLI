package eapli.base.productmanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import org.hibernate.annotations.Type;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.swing.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@Entity
public class Product implements AggregateRoot<Designation>, DTOable<ProductDTO>, Representationable {

    /**
     * Unique internal code of the product
     */
    @Id
    @GeneratedValue
    private Long internalCode;

    @Embedded
    @XmlElement
    @JsonProperty
    @AttributeOverride(name = "name", column = @Column(name = "designation"))
    private Designation name;

    /**
     * Short description of the product
     */
    @Embedded
    @XmlElement
    @JsonProperty
    @AttributeOverride(name = "value", column = @Column(name = "shortDescription"))
    private Description shortDescription;

    /**
     * Extended description of the product
     */
    @Embedded
    @XmlElement
    @JsonProperty
    @AttributeOverride(name = "value", column = @Column(name = "extendedDescription"))
    private Description extendedDescription;

    /**
     * Technical description of the product
     */
    @Embedded
    @XmlElement
    @JsonProperty
    @AttributeOverride(name = "value", column = @Column(name = "technicalDescription"))
    private Description technicalDescription;

    /**
     * Brand name of the product
     */
    @Embedded
    @XmlElement
    @JsonProperty
    @AttributeOverride(name = "brand", column = @Column(name = "brand"))
    private Designation brand;

    /**
     * Brand reference of the product(max 23 char)
     */
    @Embedded
    @XmlElement
    @JsonProperty
    @AttributeOverride(name = "reference", column = @Column(name = "reference"))
    private Reference reference;

    /**
     * Indicator of product's status. True if product is activated.
     */
    @XmlElement
    @JsonProperty
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private boolean active;

    /**
     * Price of the product after taxes
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "unitaryPosTaxPrice")),
            @AttributeOverride(name = "currency", column = @Column(name = "currencyOfUnitaryPosTaxPrice"))

    })
    private Cash unitaryPosTaxPrice;

    /**
     * Price of the product before taxes
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "unitaryPreTaxPrice")),
            @AttributeOverride(name = "currency", column = @Column(name = "currencyOfUnitaryPreTaxPrice"))

    })
    private Cash unitaryPreTaxPrice;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "format", column = @Column(name = "formatBarCode")),
            @AttributeOverride(name = "code", column = @Column(name = "barCode"))

    })
    private BarCode barCode;

    @Column(name="ProductionCode")
    private int productionCode;

    @ElementCollection
    //@Basic(fetch=FetchType.LAZY)
    private Set<Photo> photosCollection;

    /**
     * Unique category from the product. One category has many products.
     */
    @XmlElement
    @JsonProperty
    @ManyToOne(optional = false)
    private ProductCategory category;

    public void setName(Designation name) {
        this.name = name;
    }

    /**
     * @param shortDescription     short description
     * @param extendedDescription  extended description
     * @param technicalDescription more technical description
     * @param brand                brand name
     * @param reference            reference of brand
     * @param unitaryPreTaxPrice   unitary price pre tax
     * @param unitaryPosTaxPrice   unitary price pos tax
     */
    protected Product(ProductCategory category, Designation name, Description shortDescription, Description extendedDescription, Description technicalDescription, Designation brand, Reference reference, Cash unitaryPreTaxPrice, Cash unitaryPosTaxPrice, BarCode barCode, int productionCode, Set<Photo> photos) throws Exception {

        Preconditions.noneNull(category, name, shortDescription, extendedDescription, technicalDescription, brand, reference, unitaryPreTaxPrice, unitaryPosTaxPrice);

        setCategory(category);
        this.name = name;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.technicalDescription = technicalDescription;
        this.brand = brand;
        this.reference = reference;
        this.active = true;
        this.unitaryPreTaxPrice = unitaryPreTaxPrice;
        this.unitaryPosTaxPrice = unitaryPosTaxPrice;
        if(barCode == null) {
            throw new IllegalArgumentException();
        }
        this.barCode = barCode;
        if(productionCode==0){
            throw new IllegalArgumentException();
        } else {
            this.productionCode = productionCode;
        }
        this.photosCollection = photos;
    }

    protected Product(ProductCategory category, Designation name, Description shortDescription, Description extendedDescription, Description technicalDescription, Designation brand, Reference reference, Cash unitaryPreTaxPrice, Cash unitaryPosTaxPrice, BarCode barCode, int productionCode) throws Exception {

        Preconditions.noneNull(category, name, shortDescription, extendedDescription, technicalDescription, brand, reference, unitaryPreTaxPrice, unitaryPosTaxPrice);

        setCategory(category);
        this.name = name;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.technicalDescription = technicalDescription;
        this.brand = brand;
        this.reference = reference;
        this.active = true;
        this.unitaryPreTaxPrice = unitaryPreTaxPrice;
        this.unitaryPosTaxPrice = unitaryPosTaxPrice;
        this.barCode = barCode;
        if(productionCode==0){
            throw new IllegalArgumentException();
        } else {
            this.productionCode = productionCode;
        }
        this.photosCollection = new HashSet<>();
    }

    protected Product() {
        //Empty
    }

    public void setCategory(ProductCategory category) throws Exception {
        if(category != null) {
            this.category = category;
        } else {
            throw new NullPointerException();
        }
    }

    public Designation getName() {
        return name;
    }

    public Description getShortDescription() {
        return shortDescription;
    }

    public Description getExtendedDescription() {
        return extendedDescription;
    }

    public Description getTechnicalDescription() {
        return technicalDescription;
    }

    public Designation getBrand() {
        return brand;
    }

    public Reference getReference() {
        return reference;
    }

    public Cash getUnitaryPreTaxPrice() {
        return unitaryPreTaxPrice;
    }

    public Cash getUnitaryPosTaxPrice() {
        return unitaryPosTaxPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public Long getInternalCode() {
        return internalCode;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Product)) {
            return false;
        }

        final Product that = (Product) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && unitaryPreTaxPrice.equals(that.unitaryPreTaxPrice) && unitaryPosTaxPrice.equals(that.unitaryPosTaxPrice) && active == that.active && barCode == that.barCode;
    }

    /**
     * @return Name of the product
     */
    @Override
    public Designation identity() {
        return this.name;
    }


    /**
     * @return Price of the product
     */
    public Cash getPosPrice() {
        return this.unitaryPosTaxPrice;
    }

    /**
     * @return Price of the product
     */
    public Cash getPrePrice() {
        return this.unitaryPreTaxPrice;
    }

    /**
     * @return true or false whether is or not active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the product, activating it or deactivating it accordingly.
     *
     * @return whether the product is active or not
     */
    public boolean toogleState() {
        this.active = !this.active;
        return isActive();
    }

    public void changePriceTo(final Cash preNewPrice, Cash pewPosPrice) {
        unitaryPreTaxPrice = preNewPrice;
        unitaryPosTaxPrice = pewPosPrice;
    }

    public boolean addPhotos(final Photo photo){
        return photosCollection.add(photo);
    }

    @Override
    public ProductDTO toDTO() {
        return new ProductDTO(category.identity().toString(), name.toString(), shortDescription.toString(), extendedDescription.toString(), technicalDescription.toString(), brand.toString(), reference.toString(), active, unitaryPreTaxPrice.amountAsDouble(), unitaryPosTaxPrice.amountAsDouble());
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Product").withProperty("Code", internalCode)
                .withProperty("Category", category.toString())
                .withProperty("Short description", shortDescription)
                .withProperty("Extended description", extendedDescription)
                .withProperty("Technical description", technicalDescription)
                .withProperty("Brand", brand)
                .withProperty("Reference", reference.toString())
                .withProperty("Active", active)
                .withProperty("Price before taxes", String.valueOf(unitaryPreTaxPrice))
                .withProperty("Price after taxes", String.valueOf(unitaryPosTaxPrice))
                .withProperty("Barcode", String.valueOf(barCode))
                .withProperty("Production code", String.valueOf(productionCode))
                .withProperty("Photos ", String.valueOf(photosCollection))
                .startObject("ProductCategory")
                .withProperty("Code", category.getCode().toString())
                .withProperty("Description", category.description()).endObject();

        return builder.build();
    }

    @Override
    public String toString() {
        try {
            return "Product{" +
                    "internalCode=" + internalCode +
                    ", name=" + name +
                    ", shortDescription=" + shortDescription +
                    ", extendedDescription=" + extendedDescription +
                    ", technicalDescription=" + technicalDescription +
                    ", brand=" + brand +
                    ", reference=" + reference +
                    ", active=" + active +
                    ", unitaryPreTaxPrice=" + unitaryPreTaxPrice +
                    ", unitaryPosTaxPrice=" + unitaryPosTaxPrice +
                    ", barcode=" + barCode +
                    ", productionCode=" + productionCode +
                    ", category=" + category +
                    ", photos opening..." + printSetPhotos() +
                    '}';
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String printSetPhotos() throws IOException {
        for(Photo p : photosCollection) {
            BufferedImage image = ImageIO.read( new ByteArrayInputStream( p.getPhoto() ) );
            ImageIO.write(image, "BMP", new File("filename.bmp"));

            ImageIcon imageIcon = new ImageIcon(image);
            JFrame jFrame = new JFrame();

            jFrame.setLayout(new FlowLayout());
            jFrame.setTitle(this.extendedDescription.toString());

            jFrame.setSize(500, 500);
            JLabel jLabel = new JLabel();

            jLabel.setIcon(imageIcon);
            jFrame.add(jLabel);
            jFrame.setVisible(true);

        }

        return "Every image showed";
    }
}
