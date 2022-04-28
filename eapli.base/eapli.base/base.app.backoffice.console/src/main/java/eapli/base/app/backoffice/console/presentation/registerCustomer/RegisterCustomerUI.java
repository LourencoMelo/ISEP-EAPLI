package eapli.base.app.backoffice.console.presentation.registerCustomer;

import eapli.base.customermanagement.application.RegisterCustomerController;
import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.RawPassword;
import eapli.base.customermanagement.dto.CustomerDto;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;
import java.util.Set;

public class RegisterCustomerUI extends AbstractUI {

    public String gender;
    public String streetName;
    public int doorNumber;
    public String postalCode;
    public String city;
    public String country;
    public int day;
    public int month;
    public int year;
    public int select;

    private final RegisterCustomerController registerCustomerController;

    public RegisterCustomerUI(){
        this.registerCustomerController = new RegisterCustomerController();
    }

    @Override
    protected boolean doShow() {
        final String firstName = Console.readLine("First name: ");
        final String lastName = Console.readLine("Last name: ");
        final String email = Console.readLine("Email: ");
        select = Console.readInteger("Do you want to add your gender? \n 0 -> Male \n 1 -> Female \n 2 -> NO");
        switch (select) {
            case 0:
               this.gender = "Male";
               break;
            case 1:
                this.gender = "Female";
                break;
            case 2:
                this.gender = "Undefined";
                break;
            default:
                System.out.println("Command does not exist");
                break;
        }
        final long phone = Console.readLong("Phone number: ");
        final String vat = Console.readLine("Value-Added Tax: ");
        select = Console.readInteger("Do you want to add your birthday? \n 0 -> YES \n 1 -> NO");
        switch (select) {
            case 0:
                this.day = Console.readInteger("Day of birth: ");
                this.month = Console.readInteger("Month of birth: ");
                this.year = Console.readInteger("Year of birth: ");
                break;
            case 1:
                this.day = 0;
                break;
            default:
                System.out.println("Command does not exist");
                break;
        }

        Set<Address> addresses = fillAddresses();

        try{
            this.registerCustomerController.registerCustomer(firstName,lastName,email,gender,phone,vat,day,month,year,addresses);
            System.out.println("Customer registered!");
        }catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a customer which already exists in the database.");
        }

        select = Console.readInteger("Do you want to add system credentials to the customer? \n 0 -> YES \n 1 -> NO");
        if(select == 0){
            String userName = Console.readLine("Enter the customer username: ");
            String raw  = RawPassword.createPwd();
            try{
                registerCustomerController.saveCustomerAsUserWithCredentials(userName,email,raw,firstName,lastName);
            } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
                System.out.println("That username is already in use.");
            }
        }else{
            System.out.println("Our job here is done :)");
        }
        return false;
    }


    private Set<Address> fillAddresses() {
        Set<Address> addresses = new HashSet<>();
        do {
            select = Console.readInteger("Do you want to add an address? \n 0 -> YES \n 1 -> NO");
            switch (select) {
                case 0:
                    this.streetName = Console.readLine("Street name: ");
                    this.doorNumber = Console.readInteger("Door number: ");
                    this.postalCode = Console.readLine("Postal code: ");
                    this.city = Console.readLine("City: ");
                    this.country = Console.readLine("Country: ");
                    addresses.add(registerCustomerController.addressed(streetName, doorNumber, postalCode, city, country));
                    break;
                case 1:
                    System.out.println("OK!");
                    break;
                default:
                    System.out.println("Command does not exist");
                    break;
            }
        }while(select == 0);
      return addresses;
    }

    @Override
    public String headline() {
        return "Register Customer: \n";
    }
}
