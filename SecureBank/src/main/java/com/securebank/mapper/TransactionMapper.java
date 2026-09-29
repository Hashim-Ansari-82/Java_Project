package com.securebank.mapper;

import com.securebank.dto.transactiondto.TranseferRequestDto;
import com.securebank.dto.transactiondto.TranseferResponseDto;
import com.securebank.entity.Transaction;

public interface TransactionMapper {

	TranseferResponseDto entityToDto(Transaction transaction);
	Transaction dtoToEntity(TranseferRequestDto dto);
	
}
