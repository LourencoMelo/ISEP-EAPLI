package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;


/**
 * Object that represents the vat and its validations
 */
@Embeddable
public class VAT implements ValueObject {

    public String vat;

    public VAT(){

    }

    public VAT(String vat)throws IllegalArgumentException{
        Preconditions.ensure(!StringPredicates.isNullOrEmpty(vat),"VAT is null");
        this.vat = vat;
    }

}
