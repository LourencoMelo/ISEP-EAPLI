package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

/**
 * Value Object that represents an address
 */
@Embeddable
public class Address implements ValueObject {

    public String streetName;

    public int doorNumber;

    public String postalCode;

    public String city;

    public String country;

    /**
     * Address constructor
     * @param streetName street name
     * @param doorNumber door number
     * @param postalCode postal code
     * @param city city name
     * @param country country name
     */
    public Address(String streetName,int doorNumber,String postalCode,String city,String country){
        this.streetName = streetName;
        this.doorNumber = doorNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Address() {
        //Empty
    }

    @Override
    public String toString() {
        return streetName + " " + doorNumber + ", " + postalCode + ", " + city + ", " + country;
    }
}
