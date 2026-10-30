package org.example.transactionservice.repository;

import org.example.transactionservice.domain.entites.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , String> {
}
