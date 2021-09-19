package uz.spring.appanotherwarehouseproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.spring.appanotherwarehouseproject.Entity.Warehouse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String  lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private boolean active;
    private Integer warehouseId;
}
