package org.example.transactionservice.web.rest;

import org.example.transactionservice.domain.entites.Transaction;
import org.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/withdrawal")
    public ResponseEntity<String> retrait(
            @RequestBody Transaction request) {

        transactionService.retrait(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        );

        return ResponseEntity.ok("Retrait effectué avec succès");
    }
}
