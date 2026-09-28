package com.securebank.mapper;

import com.securebank.dto.transactiondto.TransactionRequestDto;
import com.securebank.dto.transactiondto.TransactionResponseDto;
import com.securebank.entity.Transaction;

public interface TransactionMapper {

	TransactionResponseDto entityToDto(Transaction transaction);
	Transaction dtoToEntity(TransactionRequestDto dto);
	
}
