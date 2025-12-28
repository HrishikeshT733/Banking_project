package com.BankingAPPSpringBoot.BankingApplication.dto;

public class LoginResponse {
    private String token;
    private boolean otpRequired;
    private AccountDto account;

    public LoginResponse() {}

    public LoginResponse(String token, boolean otpRequired, AccountDto account) {
        this.token = token;
        this.otpRequired = otpRequired;
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isOtpRequired() {
        return otpRequired;
    }

    public void setOtpRequired(boolean otpRequired) {
        this.otpRequired = otpRequired;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }
}
