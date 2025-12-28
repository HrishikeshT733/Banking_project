package com.BankingAPPSpringBoot.BankingApplication.dto;

import com.BankingAPPSpringBoot.BankingApplication.entity.Role;

public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;

    public AccountDto() {}

    public AccountDto(Long id, String accountHolderName, double balance, String email,
                      String password, String phoneNumber, Role role) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
