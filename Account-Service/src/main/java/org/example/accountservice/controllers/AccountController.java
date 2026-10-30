package org.example.accountservice.controllers;

import org.example.accountservice.entites.Account;
import org.example.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/accounts")
    public List<Account> allAccounts(){
        return accountService.allAccounts();
    }
    @GetMapping("/accounts/{id}")
    public Account findAccountById(@PathVariable String id){
        return accountService.findAccountByd(id);

    }
    @PostMapping("/accounts")
    public Account addAcount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

}
