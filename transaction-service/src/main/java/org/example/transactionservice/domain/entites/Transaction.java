package org.example.transactionservice.domain.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id @GeneratedValue
    private String id;
    private String accountId;
    private Long amount;
    private String currency ;
    private String merchantCategory;
    private Date cratedAt;
}
