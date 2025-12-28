package com.BankingAPPSpringBoot.BankingApplication.entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class DebitCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cardNumber;
    private String cvv;
    private LocalDate expiryDate;
    private String cardHolderName;
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private CardStatus status;
    private LocalDate requestedDate;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonBackReference
    private Account account;

	public DebitCard(Long id, String cardNumber, String cvv, LocalDate expiryDate, String cardHolderName,
			boolean isActive, CardStatus status, LocalDate requestedDate, Account account) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
		this.cardHolderName = cardHolderName;
		this.isActive = isActive;
		this.status = status;
		this.requestedDate = requestedDate;
		this.account = account;
	}

	public DebitCard() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

    
}

