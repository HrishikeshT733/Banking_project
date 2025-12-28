package com.BankingAPPSpringBoot.BankingApplication.service.impl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.dto.TransactionDto;
import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.entity.Role;
import com.BankingAPPSpringBoot.BankingApplication.entity.Transaction;
import com.BankingAPPSpringBoot.BankingApplication.mapper.AccountMapper;
import com.BankingAPPSpringBoot.BankingApplication.repository.AccountRepository;
import com.BankingAPPSpringBoot.BankingApplication.repository.TransactionRepository;
import com.BankingAPPSpringBoot.BankingApplication.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
	private AccountRepository accountRepository;
    
    @Autowired
    private PasswordEncoder  passwordEncoder;

	
	@Autowired
	private TransactionRepository transactionRepository;
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account=AccountMapper.mapToAccount(accountDto);
		 String encodedPassword = passwordEncoder.encode(account.getPassword());
		    account.setPassword(encodedPassword);
		    account.setRole(Role.USER);
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
		  saveTransaction(savedAccount, amount, "DEPOSIT");
		  trimTransactionHistory(savedAccount);
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
		saveTransaction(savedAccount, amount, "WITHDRAW");
		  trimTransactionHistory(savedAccount);
			return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Transactional
	@Override
	public AccountDto transferAmount(Long fid, double amount, Long tid) {
		Account account=accountRepository.findById(fid).orElseThrow(()->new RuntimeException("Account does not exist"));
		Account account2=accountRepository.findById(tid).orElseThrow(()->new RuntimeException("Receiver Account does not exist"));
		if(fid.equals(tid)) {
			throw new RuntimeErrorException(null,"Cannot Commit Transaction on same account no");
		}
		//

		if(account.getBalance()<amount){
			throw new RuntimeErrorException(null, "Insufficient Balance");
			
		}
		double totalBalance=account.getBalance()-amount;
		account.setBalance(totalBalance);
		Account savedAccount=accountRepository.save(account);
		  saveTransaction(savedAccount, amount, "TRANSFERED TO ACCOUNT NO "+account2.getId());
		  trimTransactionHistory(savedAccount);
		AccountMapper.mapToAccountDto(savedAccount);
		
	
		double totalBalance2=account2.getBalance()+amount;
		account2.setBalance(totalBalance2);
		Account savedAccount2=accountRepository.save(account2);
		  saveTransaction(savedAccount2, amount, "CREDITED FROM ACCOUNT NO "+account.getId());
		  trimTransactionHistory(savedAccount2);
		 AccountMapper.mapToAccountDto(savedAccount2);
		
		//
//		AccountDto updatedAccount=withdraw(fid,amount);
//		deposit(tid,amount);
//		return updatedAccount;
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




	@Override
	public List<TransactionDto> getStatementById(Long accountId) {
		 Account account = accountRepository.findById(accountId)
	                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + accountId));

	        List<Transaction> transactions = transactionRepository.findByAccount(account);

	        return transactions.stream()
	                .map(transaction -> new TransactionDto(
	                        transaction.getId(),
	                        transaction.getAmount(),
	                        transaction.getTimestamp(),
	                        transaction.getType(),
	                        accountId
	                ))
	                .collect(Collectors.toList());
		
	}
	  public void saveTransaction(Account account, double amount, String type) {
	        Transaction transaction = new Transaction();
	        transaction.setAccount(account);
	        transaction.setAmount(amount);
	        transaction.setTimestamp(LocalDateTime.now());
	        transaction.setType(type);
	        transactionRepository.save(transaction);
	    }

	    //  save transactions to last 50
	  public void trimTransactionHistory(Account account) {
	        List<Transaction> transactions = transactionRepository.findByAccountOrderByTimestampDesc(account);
	        if (transactions.size() > 50) {
	            List<Transaction> toDelete = transactions.subList(50, transactions.size());
	            transactionRepository.deleteAll(toDelete);
	        }
	    }




		

}
