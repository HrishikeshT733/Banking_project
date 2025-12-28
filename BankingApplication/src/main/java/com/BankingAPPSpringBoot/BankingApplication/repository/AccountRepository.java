package com.BankingAPPSpringBoot.BankingApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BankingAPPSpringBoot.BankingApplication.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	Optional<Account> findByEmail(String email);
	Optional<Account> findByPhoneNumber(String phoneNumber);
}
