package com.BankingAPPSpringBoot.BankingApplication.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

@Service
public class TwilioVerifyService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.verifyServiceSid}")
    private String verifyServiceSid;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public void sendOtp(String phoneNumber) {
        Verification.creator(verifyServiceSid, phoneNumber, "sms").create();
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
    	
        VerificationCheck check = VerificationCheck.creator(verifyServiceSid)
                .setTo(phoneNumber)
                .setCode(otp)
                .create();
        return "approved".equalsIgnoreCase(check.getStatus());
    }
}
