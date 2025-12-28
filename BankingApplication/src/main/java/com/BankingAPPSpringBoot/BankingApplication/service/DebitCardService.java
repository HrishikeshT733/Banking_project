package com.BankingAPPSpringBoot.BankingApplication.service;

import java.util.List;

import com.BankingAPPSpringBoot.BankingApplication.entity.CardStatus;
import com.BankingAPPSpringBoot.BankingApplication.entity.DebitCard;

public interface DebitCardService {
	DebitCard requestCard(Long accountId);
	DebitCard updateCardStatus(Long cardId, CardStatus newStatus);
	List<DebitCard>getallCards();
	DebitCard getCardById(Long accountId);
}
