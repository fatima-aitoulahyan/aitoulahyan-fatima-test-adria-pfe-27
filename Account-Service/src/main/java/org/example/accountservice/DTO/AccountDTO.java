package org.example.accountservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.accountservice.enums.Status;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AccountDTO {
    private String customerReference;
    private Status status ;
    private String currency ;
    private Double balance;
}
