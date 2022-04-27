package eapli.base.app.backoffice.console.presentation.registerCustomer;

import eapli.base.costumermanagement.application.RegisterCustomerController;
import eapli.base.costumermanagement.domain.Address;
import eapli.base.costumermanagement.dto.CustomerDto;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.Set;

public class RegisterCustomerUI extends AbstractUI {

    public String firstName;
    public String lastName;
    public String email;
    public String gender;
    public long phone;
    public String vat;
    public String streetName;
    public int doorNumber;
    public String postalCode;
    public String city;
    public String country;
    public int day;
    public int month;
    public int year;
    public CustomerDto toShow;
    public int select;

    private final RegisterCustomerController registerCustomerController;

    public RegisterCustomerUI(){
        this.registerCustomerController = new RegisterCustomerController();
    }

    @Override
    protected boolean doShow() {
        this.firstName = Console.readLine("First name: ");
        this.lastName = Console.readLine("Last name: ");
        this.email = Console.readLine("Email: ");
        this.gender = Console.readLine("Gender: ");
        this.phone = Console.readLong("Phone number: ");
        this.vat = Console.readLine("Value-Added Tax: ");
        this.day = Console.readInteger("Day of birth: ");
        this.month = Console.readInteger("Month of birth: ");
        this.year = Console.readInteger("Year of birth: ");

        Set<Address> addresses = fillAddresses();

        try{
            this.registerCustomerController.registerCustomer(firstName,lastName,email,gender,phone,vat,day,month,year,addresses);
        }catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a customer which already exists in the database.");
        }

        /*select = Console.readInteger("Do you want to add system credentials to the customer? \n 0 -> YES \n 1 -> NO");
        if(select == 0){

        }else{
            return false;
        }*/
        return false;

    }



    private Set<Address> fillAddresses() {
        Set<Address> addresses = new HashSet<>();
        boolean aux = true;
        while (aux) {
            select = Console.readInteger("Do you want to add an address? \n 0 -> YES \n 1 -> NO");
            switch (select) {
                case 0:
                    this.streetName = Console.readLine("Street name: ");
                    this.doorNumber = Console.readInteger("Door number: ");
                    this.postalCode = Console.readLine("Postal code: ");
                    this.city = Console.readLine("City: ");
                    this.country = Console.readLine("Country: ");
                    addresses.add(registerCustomerController.addressed(streetName, doorNumber, postalCode, city, country));
                case 1:
                    aux = false;
                    break;
                default:
                    System.out.println("Command does not exist");
                    break;
            }
        }
        return addresses;
    }

    @Override
    public String headline() {
        return "Register Customer: \n";
    }
}
