package com.BankingAPPSpringBoot.BankingApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction ,Long> {
	 List<Transaction> findByAccount(Account account);

	List<Transaction> findByAccountOrderByTimestampDesc(Account account);
}
