package eapli.base.productmanagement.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.StringMixin;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

public class Reference implements ValueObject, Serializable, StringMixin {

    private static final int REFERENCE_MAX_SIZE = 23;

    @Column(name = "reference")
    @XmlAttribute
    @JsonProperty
    private final String value;

    public Reference(final String name) {
        Preconditions.nonEmpty(name, "Description should neither be null or empty");
        this.value = name;
    }

    protected Reference(){
        this.value = null;
    }

    public int length() {
        return this.value.length();
    }

    public static Reference valueOf(final String name){return new Reference(name);}

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Reference)) {
            return false;
        } else {
            Reference other = (Reference) o;
            return this.value.equals(other.value);
        }
    }

    public String toString(){return this.value;}

    public int hashcode(){return (new HashCoder()).with(this.value).code();}


}
