package com.BankingAPPSpringBoot.BankingApplication.entity;




import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private LocalDateTime timestamp;

    private String type; // e.g., "DEPOSIT" or "WITHDRAW"

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference// foreign key
    private Account account;

    // Constructors
    public Transaction() {}

    public Transaction(double amount, LocalDateTime timestamp, String type, Account account) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
        this.account = account;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
