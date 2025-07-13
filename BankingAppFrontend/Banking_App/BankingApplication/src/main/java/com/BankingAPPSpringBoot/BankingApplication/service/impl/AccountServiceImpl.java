package com.BankingAPPSpringBoot.BankingApplication.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.mapper.AccountMapper;
import com.BankingAPPSpringBoot.BankingApplication.repository.AccountRepository;
import com.BankingAPPSpringBoot.BankingApplication.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
	Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		
		return AccountMapper.mapToAccountDto(account);
	}




	@Override
	public AccountDto deposit(Long id, Double amount) {
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));
		double totalBalance=account.getBalance()+amount;
		account.setBalance(totalBalance);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

  


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));

		if(account.getBalance()<amount){
			throw new RuntimeErrorException(null, "Insufficient Balance");
			
		}
		double totalBalance=account.getBalance()-amount;
		account.setBalance(totalBalance);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}




	@Override
	public List<AccountDto> getAllAccounts() {
		return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).
				collect(Collectors.toList());
		
		
	}




	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account does not exist"));

		accountRepository.delete(account);
	}

}
