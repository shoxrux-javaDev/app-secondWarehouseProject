package uz.spring.appanotherwarehouseproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDto {
    private LocalDate date;
    private Integer warehouseId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
    private Integer clientId;
}
