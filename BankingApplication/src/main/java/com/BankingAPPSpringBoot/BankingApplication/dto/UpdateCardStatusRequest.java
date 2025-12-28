package com.BankingAPPSpringBoot.BankingApplication.dto;

import com.BankingAPPSpringBoot.BankingApplication.entity.CardStatus;

public class UpdateCardStatusRequest {
	 private CardStatus status;

	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}
}
