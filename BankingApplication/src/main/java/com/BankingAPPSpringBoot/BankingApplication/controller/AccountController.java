package com.BankingAPPSpringBoot.BankingApplication.controller;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.dto.TransactionDto;
import com.BankingAPPSpringBoot.BankingApplication.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Only ADMIN can create accounts
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //  USER and ADMIN can view account by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // USER and ADMIN can deposit
    @PutMapping("/{id}/deposit")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // USER and ADMIN can withdraw
    @PutMapping("/{id}/withdraw")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);

        return ResponseEntity.ok(accountDto);
    }
    
    //USER and ADMIN can Transfer Money
    @PutMapping("/{id}/transfer")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AccountDto> transferAmount(@PathVariable Long id, @RequestBody Map<String, Object> request) {
    	  
    	Object amountObj = request.get("amount");
    	    double amount ;
    	    if (amountObj instanceof Number) {
    	        amount =((Number)amountObj).doubleValue();
    	    } else if (amountObj instanceof String) {
    	    	amount =Double.parseDouble((String)amountObj);
    	    }else {
    	    throw new IllegalArgumentException("Invalid amount"); 
    	    }
    	   
    	    Object accountObj = request.get("toId");
    	    long toId ;
    	    if (accountObj instanceof Number) {
    	    	toId=((Number)accountObj).longValue();
    	    } else if (accountObj instanceof String) {
    	    	toId=Long.parseLong((String)accountObj);
    	    }
    	    else {
    	    throw new IllegalArgumentException("Invalid accountNo");
    	    }
        AccountDto accountDto = accountService.transferAmount(id, amount, toId);
        return ResponseEntity.ok(accountDto);
    }

    // Only ADMIN can view all accounts
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDto = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDto);
    }

    // Only ADMIN can delete accounts
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(Map.of("message","Account Deleted Successfully...!"));
    }

    //  USER and ADMIN can view account statements
    @GetMapping("/{id}/statement")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<TransactionDto>> getStatementById(@PathVariable Long id) {
        List<TransactionDto> transactions = accountService.getStatementById(id);
        return ResponseEntity.ok(transactions);
    }
}
