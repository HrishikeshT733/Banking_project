package com.BankingAPPSpringBoot.BankingApplication.entity;
import javax.persistence.*;

@Entity
@Table(name="accounts")
public class Account {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
	
	@Column(name="account_holder_name")
private String accountHolderName;
private double balance;
public Account() {
	super();
	// TODO Auto-generated constructor stub
}
public Account(Long id, String accountHolderName, double balance) {
	super();
	this.id = id;
	this.accountHolderName = accountHolderName;
	this.balance = balance;
}
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


}
