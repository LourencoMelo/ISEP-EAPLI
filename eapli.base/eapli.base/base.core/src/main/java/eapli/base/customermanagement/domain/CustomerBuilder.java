package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;

import java.util.*;

/**
 * Class creates customer objects (customer builder)
 */
public class CustomerBuilder implements DomainFactory<Customer> {

    private Customer customer;

    private Name name;

    private EmailAddress email;

    private Gender gender;

    private PhoneNumber phoneNumber;

    private VAT vat;

    private BirthDate birthDate;

    /**
     * Name builder
     * @param firstName first name
     * @param lastName last name
     * @return name
     */
    public CustomerBuilder named(final String firstName,final String lastName){
        return named(Name.valueOf(firstName,lastName));
    }

    private CustomerBuilder named(final Name name) {
        this.name = name;
        return this;
    }

    /**
     * Email builder
     * @param email email
     * @return email address
     */
    public CustomerBuilder emailed(final String email){
        return emailed(EmailAddress.valueOf(email));
    }

    public CustomerBuilder emailed(final EmailAddress email) {
        this.email = email;
        return this;
    }

    /**
     * Phone number builder
     * @param phoneNumber phone number
     * @return phone number
     */
    public CustomerBuilder phoned(final long phoneNumber){
        return phoned(new PhoneNumber(phoneNumber));
    }

    public CustomerBuilder phoned(final PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * VAT builder
     * @param vat vat
     * @return vat
     */
    public CustomerBuilder vated(final String vat){
        return vated(new VAT(vat));
    }

    public CustomerBuilder vated(final VAT vat) {
        this.vat = vat;
        return this;
    }

    public CustomerBuilder gendered(final String gender){
        if(gender.equals("Male")){
            return gendered(Gender.Masculine);
        }else if(gender.equals("Female")){
            return gendered(Gender.Feminine);
        }
        return gendered(Gender.Non_Defined);
    }

    public CustomerBuilder gendered(final Gender gender){
        this.gender = gender;
        return this;
    }

    /**
     * Birthdate builder
     * @param year year
     * @param month month
     * @param day day
     * @return birthday
     */
    public CustomerBuilder dated(final int year,final int month,final int day){
        if(day == 0){
            return dated(new BirthDate());
        }
        return dated(new BirthDate(day,month,year));
    }

    public CustomerBuilder dated(final BirthDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    private Customer buildOrThrow() {
        if (customer != null) {
            return customer;
        } else if (name != null && vat != null && email != null && phoneNumber != null) {
            customer = new Customer(name,email,gender,phoneNumber,vat,birthDate);
            return customer;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Single address builder
     * @param streetName street name
     * @param doorNumber door number
     * @param postalCode postal code
     * @param city city
     * @param country country
     * @return address
     */
    public Address addressed(String streetName,int doorNumber,String postalCode,String city,String country){
        return new Address(streetName,doorNumber,postalCode,city,country);
    }

    /**
     * Set of addresses builder
     * @param addresses addresses
     * @return set
     */
    public CustomerBuilder withAddresses(final Set<Address> addresses) {
        // we will simply ignore if we receive a null set
        if (addresses != null) {
            addresses.forEach(this::withAddresses);
        }
        return this;
    }

    public CustomerBuilder withAddresses(final Address address) {
        buildOrThrow();
        customer.addAddresses(address);
        return this;
    }

    /**
     * Builds the value objects in a new entity
     * @return new customer
     */
    @Override
    public Customer build() {
        final Customer cust = buildOrThrow();
        customer = null;
        return cust;
    }
}
