package org.example.accountservice.service;

import org.example.accountservice.entites.Account;
import org.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> allAccounts(){
        return accountRepository.findAll();
    }
    public Account findAccountByd(String id){
        return accountRepository.findById(id).orElseThrow(()->new RuntimeException("customer Not Found"));

    }
    public Account addAccount(Account account){
        return accountRepository.save(account);
    }
    public Account updateBalance(String id, Long newBalance) {

        Account account = findAccountByd(id);

        account.setBalance(newBalance);

        return accountRepository.save(account);
    }




}
