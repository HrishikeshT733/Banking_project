package com.BankingAPPSpringBoot.BankingApplication.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingAPPSpringBoot.BankingApplication.Exception.DebitCardNotFoundException;
import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.entity.CardStatus;
import com.BankingAPPSpringBoot.BankingApplication.entity.DebitCard;
import com.BankingAPPSpringBoot.BankingApplication.repository.AccountRepository;
import com.BankingAPPSpringBoot.BankingApplication.repository.DebitCardRepository;
import com.BankingAPPSpringBoot.BankingApplication.service.DebitCardService;

@Service
public class DebitServiceImpl implements DebitCardService{
@Autowired 
  AccountRepository accountRepository;
@Autowired
DebitCardRepository debitCardRepository;
	@Override
	public DebitCard requestCard(Long accountId) {
		Account account=accountRepository.findById(accountId).orElseThrow(()->new RuntimeException("Account does not exist"));
		 
		DebitCard debitCard=new DebitCard();
		debitCard.setAccount(account);
		debitCard.setCardHolderName(account.getAccountHolderName());
		debitCard.setRequestedDate(LocalDate.now());
		debitCard.setStatus(CardStatus.PENDING);
		debitCardRepository.save(debitCard);
		
		return debitCard;
	}

	@Override
	public DebitCard updateCardStatus(Long cardId, CardStatus newStatus) {
		DebitCard debitCard=debitCardRepository.findById(cardId).orElseThrow(()->new RuntimeException("Debit Card Details Not Found"));
		debitCard.setStatus(newStatus);
		
		if(newStatus==CardStatus.ISSUED) {
			debitCard.setCardNumber(generateCardNumber());
			debitCard.setActive(true);
			debitCard.setCvv(generateCVV());
			debitCard.setExpiryDate(LocalDate.now().plusYears(4));
		}
		debitCardRepository.save(debitCard);
		
		return debitCard;
	}
	
	@Override
	public List<DebitCard> getallCards() {
		
		return debitCardRepository.findAll();
	}
	@Override
	public DebitCard getCardById(Long accountId) {
	     Account account=accountRepository.findById(accountId).orElseThrow(()->new RuntimeException("Account does not Exist"));
	     return debitCardRepository.findByAccount(account).orElseThrow(() -> new DebitCardNotFoundException(accountId));
	}
	
	
	private String generateCardNumber()
	{
		int attempts=0;
		 String cardNumber;
		    do {
		        cardNumber = String.valueOf(1000_0000_0000_0000L + 
		                     new Random().nextLong(9_0000_0000_0000_000L));
		        attempts++;
		        if (attempts > 30) throw new RuntimeException("Unable to generate unique card number");
		    } while (debitCardRepository.existsByCardNumber(cardNumber));
		    return cardNumber;
	}
	private String generateCVV() {
	    int cvv = (int)(Math.random() * 900) + 100; // generates number between 100 to 999
	    return String.valueOf(cvv);
	}

	



}
