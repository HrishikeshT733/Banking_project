package com.BankingAPPSpringBoot.BankingApplication.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.service.AccountService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:52343")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	@Autowired
 private AccountService accountService;
 	
 public AccountController() {
	super();
	// TODO Auto-generated constructor stub
}
	public AccountController(AccountService accountService) {
	super();
	this.accountService = accountService;
}
	//add account rest api
	@PostMapping
	public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){
		
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	//get single account details
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	//deposit
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto>deposit(@PathVariable Long id,@RequestBody Map<String, Double>request)
	{
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id, amount);
	    return ResponseEntity.ok(accountDto);
	}
	
	//withdraw
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto>withdraw(@PathVariable Long id,@RequestBody Map<String,Double>request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	  }
	
	@GetMapping
	public ResponseEntity<List<AccountDto>>getAllAccounts(){
		List<AccountDto> accountDto=accountService.getAllAccounts();
		
		return ResponseEntity.ok(accountDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteAccount(@PathVariable Long id){
		
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully...!");
		}
}
