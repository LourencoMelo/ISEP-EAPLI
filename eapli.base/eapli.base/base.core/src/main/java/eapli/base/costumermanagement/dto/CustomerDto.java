package eapli.base.costumermanagement.dto;

import eapli.framework.representations.dto.DTO;

import java.util.List;

@DTO
public class CustomerDto {

    public String firstName;

    public String secondName;

    public String email;

    public String gender;

    public String phoneNumber;

    public String vat;

    //public List<String> addresses;

    public CustomerDto(String firstName,String secondName,String email,String gender,String phoneNumber,String vat){
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.vat = vat;
    }

}
