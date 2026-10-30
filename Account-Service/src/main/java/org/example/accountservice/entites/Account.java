package org.example.accountservice.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.accountservice.enums.Status;

@Entity
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Account {
    @Id @GeneratedValue
    private String id;
    private String customerReference;
    @Enumerated
    private Status status;
    private String currency;
    private double balance;


}
