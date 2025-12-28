package com.BankingAPPSpringBoot.BankingApplication.dto;

public class OtpRequest {
    private String phone;
    private String code;

    // Getters and setters
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
