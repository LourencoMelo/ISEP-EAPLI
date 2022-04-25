package eapli.base.costumermanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class creates customer objects (customer builder)
 */
public class CustomerBuilder implements DomainFactory<Customer> {

    private Name name;

    private EmailAddress email;

    private Gender gender;

    private PhoneNumber phoneNumber;

    private VAT vat;

    private Date birthDate;

    /**
     * Creates a new Customer
     * @param firstName first name
     * @param lastName last name
     * @param email customer email
     * @param gender customer gender
     * @param phoneNumber phone number
     * @param vat customer vat
     * @param year year of birth
     * @param month month of birth
     * @param day day of birth
     * @throws IllegalArgumentException
     */
    public void create(String firstName,String lastName,String email,Gender gender,long phoneNumber,String vat,int year,int month,int day) throws IllegalArgumentException{
        //this.name = new Name(firstName,lastName);
        //this.email = new EmailAddress(email);
        this.gender = gender;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.vat = new VAT(vat);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        this.birthDate = cal.getTime();
        List<Address> addresses = new ArrayList<Address>();
    }

    /**
     * Builds the value objects in a new entity
     * @return new customer
     */
    @Override
    public Customer build() {
        return new Customer(name,email,gender,phoneNumber,vat,birthDate);
    }
}
