package eapli.base.warehousemanagement.dto;


import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportFileWarehouseDTO {

    public String fileName;
}
