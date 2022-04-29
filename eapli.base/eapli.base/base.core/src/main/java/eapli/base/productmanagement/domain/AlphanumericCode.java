package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class AlphanumericCode implements ValueObject, Comparable<AlphanumericCode> {

    private static final long serialVersionUID = 1L;

    /**
     * Maximum size of alphanumeric code
     */
    private static final int MAX_CHAR = 23;

    private String code;

    public AlphanumericCode(final String alphanumericCode){
        if (StringPredicates.isNullOrEmpty(alphanumericCode)){
            throw new IllegalArgumentException("Alphanumeric code should neither be null nor empty");
        } else if (alphanumericCode.length() > MAX_CHAR) {
            throw new IllegalArgumentException("Alphanumeric code should not have more than 23 char");
        }
        this.code = alphanumericCode;
    }

    public AlphanumericCode() {

    }

    public static AlphanumericCode valueOf(String alphanumericCode){
        return new AlphanumericCode(alphanumericCode);
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public int compareTo(AlphanumericCode o) {
        return code.compareTo(o.code);
    }
}
