package eapli.base.costumermanagement.domain;

import eapli.framework.domain.model.ValueObject;

/**
 * Value Object that represents an address
 */
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
}
