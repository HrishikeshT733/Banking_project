package com.BankingAPPSpringBoot.BankingApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BankingAPPSpringBoot.BankingApplication.dto.UpdateCardStatusRequest;
import com.BankingAPPSpringBoot.BankingApplication.entity.CardStatus;
import com.BankingAPPSpringBoot.BankingApplication.entity.DebitCard;
import com.BankingAPPSpringBoot.BankingApplication.service.DebitCardService;

@RestController
@RequestMapping("/api/admin/debitCard")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCardController {

	@Autowired
	private DebitCardService debitCardService;
	
	@PutMapping("/{cardId}/status")
	   public ResponseEntity<?> updateStatus(@PathVariable Long cardId, @RequestBody UpdateCardStatusRequest request) {
        DebitCard updated = debitCardService.updateCardStatus(cardId, request.getStatus());
        System.out.println(request.getStatus());
        return ResponseEntity.ok(updated);
    }
	
	  @GetMapping("/all")
	    public ResponseEntity<List<DebitCard>> getAllCardRequests() {
	        return ResponseEntity.ok(debitCardService.getallCards());
	    }
	
}
