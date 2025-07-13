package com.BankingAPPSpringBoot.BankingApplication.service;

import java.util.List;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;

public interface AccountService {
AccountDto createAccount(AccountDto account);
AccountDto getAccountById(Long id);
AccountDto deposit(Long id,Double amount);
AccountDto withdraw(Long id,double amount);

List<AccountDto>getAllAccounts();

void deleteAccount(Long id);
}
