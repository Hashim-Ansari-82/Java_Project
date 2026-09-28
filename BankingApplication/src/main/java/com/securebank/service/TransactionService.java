package com.securebank.service;

import java.util.List;

import com.securebank.dto.transactiondto.TransactionRequestDto;
import com.securebank.dto.transactiondto.TransactionResponseDto;


public interface TransactionService {

	TransactionResponseDto transfer(String email,TransactionRequestDto dto);
	List<TransactionResponseDto> getAllTransaction();
	List<TransactionResponseDto> getTransactionByEmail(String email);
	void delete(Integer id);
}
