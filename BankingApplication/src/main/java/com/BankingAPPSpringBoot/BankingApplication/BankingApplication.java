package com.BankingAPPSpringBoot.BankingApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.BankingAPPSpringBoot.BankingApplication.entity.Account;
import com.BankingAPPSpringBoot.BankingApplication.entity.Role;
import com.BankingAPPSpringBoot.BankingApplication.repository.AccountRepository;

@SpringBootApplication
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}
	  @Bean
	    public CommandLineRunner initAdmin(AccountRepository repo, PasswordEncoder encoder) {
	        return args -> {
	            String adminEmail = "mickeyroxx72@gmail.com";
	            if (repo.findByEmail(adminEmail).isEmpty()) {
	                Account admin = new Account();
	                admin.setAccountHolderName("Admin");
	                admin.setEmail(adminEmail);
	                admin.setPassword(encoder.encode("admin123")); // secure hashing
	                admin.setBalance(0.0);
	                admin.setPhoneNumber("+918788324661"); // dummy
	                admin.setRole(Role.ADMIN);
	                repo.save(admin);
	                System.out.println("Default admin user created");
	            }
	        };
	    }

}
