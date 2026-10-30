package org.example.accountservice.web.graphql.controller;

import org.example.accountservice.entites.Account;
import org.example.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AccountGraphQLController {
    @Autowired
    private AccountService accountService;
    @QueryMapping("/accounts")
    public List<Account> allAccounts(){
        return accountService.allAccounts();
    }
    @QueryMapping("/accounts/{id}")
    public Account findAccountById(@Argument String id){
        return accountService.findAccountByd(id);

    }
    @MutationMapping("/accounts")
    public Account addAcount(@Argument AccountDTO account){
        return accountService.addAccount(account);
    }
}
