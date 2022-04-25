package eapli.base.costumermanagement.domain;

import eapli.base.costumermanagement.dto.CustomerDto;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Customer class
 */
@Entity
public class Customer implements AggregateRoot<Long>, DTOable<CustomerDto> {

    @Id
    @GeneratedValue
    private Long id;

    private Name name;

    private EmailAddress email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private PhoneNumber phoneNumber;

    private VAT vat;

    private List<Address> addresses;

    private Date birthDate;

    /**
     * Empty constructor
     */
    public Customer(){

    }

    /**
     * Customer constructor
     * @param name customer name
     * @param email customer email
     * @param gender customer gender
     * @param phoneNumber customer phone number
     * @param vat customer value-added tax
     */
    public Customer(Name name, EmailAddress email, Gender gender, PhoneNumber phoneNumber, VAT vat,Date birthDate){
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.vat = vat;
        this.birthDate = birthDate;
        addresses = new ArrayList<Address>();
    }

    public EmailAddress getEmailAddress(){
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }


    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public VAT getVat() {
        return vat;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * Adds an address
     * @param address address
     * @return true if address added
     */
    public boolean addAddresses(Address address){
        return addresses.add(address);
    }

    /**
     * Converts a customer object to a data transfer object
     * @return customerDto
     */
    @Override
    public CustomerDto toDTO(){
        return new CustomerDto(name.firstName(),name.lastName(),email.toString(),gender.toString(),phoneNumber.toString(),vat.toString());
    }

    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(other == null)
            return false;
        if(getClass() != other.getClass())
            return false;
        Customer cost = (Customer) other;
        return Objects.equals(id, cost.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
