package com.securebank.service;

import java.util.List;

import com.securebank.dto.transactiondto.DepositRequestDto;
import com.securebank.dto.transactiondto.DepositResponseDto;
import com.securebank.dto.transactiondto.TranseferRequestDto;
import com.securebank.dto.transactiondto.TranseferResponseDto;


public interface TransactionService {

	TranseferResponseDto transfer(String email,TranseferRequestDto dto);
	DepositResponseDto deposit(String email,DepositRequestDto dto);
	List<TranseferResponseDto> getAllTransaction();
	List<TranseferResponseDto> getTransactionByEmail(String email);
	void delete(Integer id);
}
