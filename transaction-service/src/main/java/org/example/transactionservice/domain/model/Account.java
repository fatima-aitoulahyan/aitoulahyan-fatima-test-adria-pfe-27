package org.example.transactionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.transactionservice.domain.enums.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Account {
    private String id;
    private String customerReference;
    private Status status ;
    private String currency ;
    private Double balance;
}
