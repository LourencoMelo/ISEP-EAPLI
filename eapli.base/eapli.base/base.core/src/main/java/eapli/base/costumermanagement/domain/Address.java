package eapli.base.costumermanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class Address implements ValueObject {

    public String address;

    public AddressType type;

    public Address(String address,AddressType type){
        this.address = address;
        this.type = type;
    }
}
