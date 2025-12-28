package com.BankingAPPSpringBoot.BankingApplication.mapper;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.entity.Account;

public class AccountMapper {
public static Account mapToAccount(AccountDto accountDto){
	
	Account account =new Account(accountDto.getId(),accountDto.getAccountHolderName(),accountDto.getBalance()
			,accountDto.getEmail(),accountDto.getPassword(),accountDto.getPhoneNumber(),accountDto.getRole());
	
	return account;
}
public static AccountDto mapToAccountDto(Account account){
	AccountDto accountDto=new AccountDto(
		 account.getId(),
		 account.getAccountHolderName(),
		 account.getBalance(),
		 account.getEmail(),account.getPassword(),account.getPhoneNumber(),account.getRole()
		 );
	return accountDto;
			
	
}
}
