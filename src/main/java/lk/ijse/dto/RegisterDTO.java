package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RegisterDTO {
    private String stuId;
    private String couId;
    private LocalDate date;
    private double payment;
    private double amountPay;
}
