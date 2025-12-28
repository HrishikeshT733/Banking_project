package com.BankingAPPSpringBoot.BankingApplication.service;

import java.util.List;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.dto.TransactionDto;

public interface AccountService {
AccountDto createAccount(AccountDto account);
AccountDto getAccountById(Long id);
AccountDto deposit(Long id,Double amount);
AccountDto withdraw(Long id,double amount);
AccountDto transferAmount(Long id, double amount,Long tid);
List<TransactionDto> getStatementById(Long accountId);

List<AccountDto>getAllAccounts();

void deleteAccount(Long id);
}
