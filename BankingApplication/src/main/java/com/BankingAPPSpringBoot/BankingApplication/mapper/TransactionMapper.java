package com.BankingAPPSpringBoot.BankingApplication.mapper;

import com.BankingAPPSpringBoot.BankingApplication.dto.TransactionDto;
import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.entity.Transaction;

public class TransactionMapper {

    public static TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
            transaction.getId(),
            transaction.getAmount(),
            transaction.getTimestamp(),
            transaction.getType(),
            transaction.getAccount().getId()
        );
    }

    public static Transaction toEntity(TransactionDto dto, Account account) {
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setTimestamp(dto.getTimestamp());
        transaction.setType(dto.getType());
        transaction.setAccount(account);
        return transaction;
    }
}

