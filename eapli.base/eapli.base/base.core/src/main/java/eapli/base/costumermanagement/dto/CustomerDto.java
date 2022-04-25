package eapli.base.costumermanagement.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@DTO
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    public String firstName;

    public String secondName;

    public String email;

    public String gender;

    public String phoneNumber;

    public String vat;
}
