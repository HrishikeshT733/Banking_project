package com.BankingAPPSpringBoot.BankingApplication.entity;


import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    private double balance;

    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transaction> transactions;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private DebitCard debitCard;

    public DebitCard getDebitCard() {
		return debitCard;
	}

	public void setDebitCard(DebitCard debitCard) {
		this.debitCard = debitCard;
	}

	// Constructors
    public Account() {}

    public Account(Long id,String accountHolderName, double balance, String email, String password, String phoneNumber, Role  role) {
       this.id=id;
    	this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role  getRole() {
        return role;
    }

    public void setRole(Role  role) {
        this.role = role;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
