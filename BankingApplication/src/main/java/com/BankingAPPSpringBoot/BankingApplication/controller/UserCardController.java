package com.BankingAPPSpringBoot.BankingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankingAPPSpringBoot.BankingApplication.entity.DebitCard;
import com.BankingAPPSpringBoot.BankingApplication.service.DebitCardService;

@RestController
@RequestMapping("/api/user/debitCard")
@PreAuthorize("hasRole('USER')")
public class UserCardController {
 @Autowired
 DebitCardService  debitCardService;
 
 
 @PostMapping("/requestCard/{accountId}")
 public ResponseEntity<?>requestCard(@PathVariable Long accountId){
	 
	 DebitCard debitCard=debitCardService.requestCard(accountId);
	 return ResponseEntity.ok(debitCard);
 }
 
@GetMapping("/{accountId}")
	public ResponseEntity<?>getMyCard(@PathVariable Long accountId){
		DebitCard debitCard=debitCardService.getCardById(accountId);
		return ResponseEntity.ok(debitCard);
	}

	
}
