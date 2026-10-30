package org.example.transactionservice.service;

import org.example.transactionservice.domain.Feign.AccountRestClient;
import org.example.transactionservice.domain.model.Account;
import org.example.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRestClient accountRestClient;

    @Value("${transaction.max-amount:10000}")
    private Long maxAmount;

    @Transactional
    public void retrait(String accountId, Long amount, String currency) {

        if (amount == null || amount <= 0) {
            throw new RuntimeException("Le montant doit être strictement positif");
        }

        if (amount > maxAmount) {
            throw new RuntimeException("Le montant dépasse le plafond autorisé");
        }

        Account account = accountRestClient.getAccountById(accountId);

        if (account == null) {
            throw new RuntimeException("Compte introuvable");
        }

        if ("BLOCKED".equals(account.getStatus())) {
            throw new RuntimeException("Le compte est bloqué");
        }
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("La devise ne correspond pas au compte");
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Solde insuffisant");
        }

        Double newBalance = account.getBalance() - amount;

        accountRestClient.updateBalance(accountId, newBalance);

    }
}