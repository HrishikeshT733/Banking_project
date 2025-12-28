package com.BankingAPPSpringBoot.BankingApplication.Exception;

public class DebitCardNotFoundException extends RuntimeException{
	public DebitCardNotFoundException(Long accountId) {
        super("Debit Card doesn't exist for account ID " + accountId);
    }
}
