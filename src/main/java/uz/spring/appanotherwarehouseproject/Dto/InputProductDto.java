package uz.spring.appanotherwarehouseproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {
    private Integer productId;
    private Integer amount;
    private String price;
    private LocalDate expireDate;
    private Integer inputId;
}
