package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;


/**
 * Object that represents the phoneNumber and its validations
 */
@Embeddable
public class PhoneNumber implements ValueObject {

    public long phoneNumber;

    public PhoneNumber(){

    }

    public PhoneNumber(long phoneNumber)throws IllegalArgumentException{
        boolean aux = phoneNumber > 0 && phoneNumber < 999999999;
        Preconditions.ensure(aux, "The phone number inserted is invalid");
        this.phoneNumber = phoneNumber;
    }

}
