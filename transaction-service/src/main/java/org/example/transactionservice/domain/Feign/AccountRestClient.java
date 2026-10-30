package org.example.transactionservice.domain.Feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.transactionservice.domain.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface AccountRestClient {

        @GetMapping("/accounts/{id}")
        @CircuitBreaker(name = "AccountService", fallbackMethod = "getDefaultCustomer")
        Account getAccountById(@PathVariable String id);
        @PutMapping("/accounts/{id}/balance")
        void updateBalance(
                @PathVariable("id") String id,
                @RequestBody Double newBalance
        );
        default Account getDefaultAccount(String  id ,Exception e ){
            return new Account("Not","Not",null,"Not",null);
        }

}
