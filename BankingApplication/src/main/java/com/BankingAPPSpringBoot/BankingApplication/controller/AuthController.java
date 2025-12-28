package com.BankingAPPSpringBoot.BankingApplication.controller;

import com.BankingAPPSpringBoot.BankingApplication.dto.AccountDto;
import com.BankingAPPSpringBoot.BankingApplication.dto.AuthRequest;
import com.BankingAPPSpringBoot.BankingApplication.dto.LoginResponse;
import com.BankingAPPSpringBoot.BankingApplication.dto.ChangePasswordRequest;
import com.BankingAPPSpringBoot.BankingApplication.dto.OtpRequest;
import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.mapper.AccountMapper;
import com.BankingAPPSpringBoot.BankingApplication.repository.AccountRepository;
import com.BankingAPPSpringBoot.BankingApplication.security.JwtUtil;
import com.BankingAPPSpringBoot.BankingApplication.security.TwilioVerifyService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TwilioVerifyService twilioVerifyService;
    
    @Autowired
    private PasswordEncoder  passwordEncoder;


    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        // Load account
        Account account = accountRepository.findByEmail(request.getUsername())
            .orElseThrow(() -> new RuntimeException("Account not found"));

        AccountDto accountDto = AccountMapper.mapToAccountDto(account);

        // Admin? Send OTP first
 //***********//temperory close OTP based authentication--Date-23/07/2025
        
//     if (account.getRole().name().equalsIgnoreCase("ADMIN")) {
//         twilioVerifyService.sendOtp(account.getPhoneNumber());
//          LoginResponse response = new LoginResponse(null, true, accountDto);
//           return ResponseEntity.ok(response);
//       }

        // If not admin, return JWT immediately
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);
        LoginResponse response = new LoginResponse(jwtToken, false, accountDto);
        return ResponseEntity.ok(response);
    }


//    @PostMapping("/verify-otp")
//    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {
//        boolean isVerified = twilioVerifyService.verifyOtp(request.getPhone(), request.getCode());
//
//        if (!isVerified) {
//            return ResponseEntity.status(401).body("Invalid OTP");
//        }
//
//        Account account = accountRepository.findByPhoneNumber(request.getPhone())
//            .orElseThrow(() -> new RuntimeException("Account not found"));
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(account.getEmail());
//        String jwtToken = jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthResponse(jwtToken, account.getRole().name()));
//    }
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {
        boolean isVerified = twilioVerifyService.verifyOtp(request.getPhone(), request.getCode());

        if (!isVerified) {
            return ResponseEntity.status(401).body("Invalid OTP");
        }

        Account account = accountRepository.findByPhoneNumber(request.getPhone())
            .orElseThrow(() -> new RuntimeException("Account not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(account.getEmail());
        String jwtToken = jwtUtil.generateToken(userDetails);
        AccountDto accountDto = AccountMapper.mapToAccountDto(account);

        LoginResponse response = new LoginResponse(jwtToken, false, accountDto);
        return ResponseEntity.ok(response);
    }

    
    @PutMapping("/login/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Authentication authentication) {
        String email = authentication.getName(); // gets current logged-in user

        Account account = accountRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), account.getPassword())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Old password is incorrect"));
        }

        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account);

        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }


}
