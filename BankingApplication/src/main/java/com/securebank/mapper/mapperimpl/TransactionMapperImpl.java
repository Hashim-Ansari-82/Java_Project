package com.securebank.mapper.mapperimpl;

import org.springframework.stereotype.Component;

import com.securebank.dto.transactiondto.TransactionRequestDto;
import com.securebank.dto.transactiondto.TransactionResponseDto;
import com.securebank.entity.Transaction;
import com.securebank.mapper.TransactionMapper;
 
@Component
public class TransactionMapperImpl implements TransactionMapper {

	@Override
	public TransactionResponseDto entityToDto(Transaction transaction) {

       TransactionResponseDto dto = new TransactionResponseDto();
		
       dto.setAmount(transaction.getAmount());
       dto.setId(transaction.getId());
       dto.setStatus(transaction.getStatus());
       dto.setTransactionReference(transaction.getTransactionReference());
       dto.setCreatedAt(transaction.getCreatedAt());
       dto.setTransactionType(transaction.getTransactionType());
       dto.setRemark(transaction.getRemark());
       
		return dto;
	}

	@Override
	public Transaction dtoToEntity(TransactionRequestDto dto) {
	
		Transaction transaction = new Transaction();
		transaction.setAmount(dto.getAmount());
		transaction.setRemark(dto.getRemark());
		
		return transaction;
	}

}
