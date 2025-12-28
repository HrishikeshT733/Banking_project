package com.BankingAPPSpringBoot.BankingApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.entity.DebitCard;
import com.BankingAPPSpringBoot.BankingApplication.entity.Transaction;

public interface DebitCardRepository extends JpaRepository<DebitCard,Long>{
	
	Optional<DebitCard> findByCardNumber(String cardNumber);
	boolean existsByCardNumber(String cardNumber);
	Optional<DebitCard> findByAccount(Account account);
	 

}
