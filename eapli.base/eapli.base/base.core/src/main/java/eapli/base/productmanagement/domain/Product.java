package eapli.base.productmanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.Money;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Product implements AggregateRoot<Designation>, DTOable<ProductDTO>, Representationable {

    /**
     * Unique internal code of the product
     */
    @Id
    @GeneratedValue
    private Long internalCode;

    @EmbeddedId
    @XmlElement
    @JsonProperty
    @AttributeOverride(name = "name", column = @Column(name = "name"))
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
    @AttributeOverride(name = "name", column = @Column(name = "brand"))
    private Designation brand;

    /**
     * Brand reference of the product(max 23 char)
     */
    @XmlElement
    @JsonProperty
    private Reference reference;

    /**
     * Indicator of product's status. True if product is activated.
     */
    @XmlElement
    @JsonProperty
    private boolean active;

    /**
     * Price of the product before taxes
     */
    @Embedded
    @AttributeOverride(name = "currency", column = @Column(name = "unitaryPreTaxPrice"))
    private Money unitaryPreTaxPrice;

    /**
     * Price of the product after taxes
     */
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "unitaryPosTaxPrice"))
    private Money unitaryPosTaxPrice;

    /**
     * Unique category from the product. One category has many products.
     */
    @XmlElement
    @JsonProperty
    @ManyToOne(optional = false)
    private ProductCategory category;

    /**
     * @param shortDescription     short description
     * @param extendedDescription  extended description
     * @param technicalDescription more technical description
     * @param brand                brand name
     * @param reference            reference of brand
     * @param unitaryPreTaxPrice   unitary price pre tax
     * @param unitaryPosTaxPrice   unitary price pos tax
     */
    protected Product(ProductCategory category, Designation name, Description shortDescription, Description extendedDescription, Description technicalDescription, Designation brand, Reference reference, Money unitaryPreTaxPrice, Money unitaryPosTaxPrice) {

        Preconditions.noneNull(category, name, shortDescription, extendedDescription, technicalDescription, brand, reference, unitaryPreTaxPrice, unitaryPosTaxPrice);

        this.category = category;
        this.name = name;
        this.shortDescription = shortDescription;
        this.extendedDescription = extendedDescription;
        this.technicalDescription = technicalDescription;
        this.brand = brand;
        this.reference = reference;
        this.active = true;
        this.unitaryPreTaxPrice = unitaryPreTaxPrice;
        this.unitaryPosTaxPrice = unitaryPosTaxPrice;
    }

    protected Product() {
        //Empty
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

    public Money getUnitaryPreTaxPrice() {
        return unitaryPreTaxPrice;
    }

    public Money getUnitaryPosTaxPrice() {
        return unitaryPosTaxPrice;
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

        return identity().equals(that.identity()) && unitaryPreTaxPrice.equals(that.unitaryPreTaxPrice) && unitaryPosTaxPrice.equals(that.unitaryPosTaxPrice) && active == that.active;
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
    public Money getPosPrice() {
        return this.unitaryPosTaxPrice;
    }

    /**
     * @return Price of the product
     */
    public Money getPrePrice() {
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

    public void changePriceTo(final Money preNewPrice, Money pewPosPrice) {
        unitaryPreTaxPrice = preNewPrice;
        unitaryPosTaxPrice = pewPosPrice;
    }

    @Override
    public ProductDTO toDTO() {
        return new ProductDTO(category.identity(), name.toString(), shortDescription.toString(), extendedDescription.toString(), technicalDescription.toString(), brand.toString(), reference.toString(), active, unitaryPreTaxPrice.amountAsDouble(), unitaryPosTaxPrice.amountAsDouble());
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
                .withProperty("Price before taxes", unitaryPreTaxPrice)
                .withProperty("Price after taxes", unitaryPosTaxPrice)
                .startObject("ProductCategory")
                .withProperty("Code", category.getCode().toString())
                .withProperty("Description", category.description()).endObject();

        return builder.build();
    }

    @Override
    public String toString() {
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
                ", category=" + category +
                '}';
    }
}
