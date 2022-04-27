package eapli.base.productmanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.base.productmanagement.dto.ProductCategoryDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "code" }) })
public class ProductCategory implements AggregateRoot<String> {

    private static final long serialVersionUID = 1L;

    /**
     * Alphanumeric code of the category
     */

    @EmbeddedId
    private AlphanumericCode code;

    /**
     * Description of the category
     */
    @Embedded
    private Description description;

    /**
     * Reference to superCategory
     */
    @ManyToOne
    @JoinColumn(name = "super_category_ID")
    private ProductCategory superCategory;

    @XmlElement
    @JsonProperty
    private boolean active;

    public ProductCategory getSuperCategory() {
        return superCategory;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Constructor with super category reference
     * @param code alphanumeric code
     * @param description description
     * @param superCategory super category
     */
    public ProductCategory(AlphanumericCode code, Description description, ProductCategory superCategory) {
        this.code = code;
        this.description = description;
        this.superCategory = superCategory;
        this.active = true;
    }

    /**
     * Constructor without super category reference
     * @param code alphanumeric code
     * @param description description
     */
    public ProductCategory(AlphanumericCode code, Description description) {
        this.code = code;
        this.description = description;
        superCategory = null;
        this.active = true;
    }

    protected ProductCategory(){
        //Empty
    }

    /**
     * Setter for new description
     * @param newDescription description to change
     */
    public void changeDescriptionTo(Description newDescription) {
        if (descriptionMeetsMinimumRequirements(description.toString())){
            this.description = newDescription;
        }else {
            throw new IllegalArgumentException("Invalid Description");
        }
    }

    /**
     * Validates new description
     * @param description new description
     * @return true if valid
     */
    private static boolean descriptionMeetsMinimumRequirements(final String description) {
        return !StringPredicates.isNullOrEmpty(description);
    }

    /**
     *
     * @return description of the category
     */
    public Description description() {
        return description;
    }

    /**
     *
     * @return current status of category. True if active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the product category, activating it or deactivating it
     * accordingly.
     *
     * @return whether the category is active or not
     */
    public boolean toogleState() {

        this.active = !this.active;
        return isActive();
    }

    @Override
    public boolean sameAs(Object other) {
        final ProductCategory category = (ProductCategory) other;

        return this.equals(category) && this.description.equals(category.description) && this.isActive() == category.isActive();
    }

    @Override
    public String identity() {
        return code.toString();
    }

    @Override
    public String toString() {
        return identity();
    }

    public AlphanumericCode getCode() {
        return code;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    public ProductCategoryDTO toDTO() {
        return new ProductCategoryDTO(String.valueOf(code), String.valueOf(description), active);
    }

    @Override
    public boolean equals(Object obj) {
        return DomainEntities.areEqual(this, obj);
    }
}
