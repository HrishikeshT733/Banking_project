package com.BankingAPPSpringBoot.BankingApplication.dto;

import java.time.LocalDateTime;

public class TransactionDto {

    private Long id;
    private double amount;
    private LocalDateTime timestamp;
    private String type;
    private Long accountId;

    public TransactionDto() {}

    public TransactionDto(Long id, double amount, LocalDateTime timestamp, String type, Long accountId) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
        this.accountId = accountId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
