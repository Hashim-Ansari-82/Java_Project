package com.securebank.mapper;

import com.securebank.dto.accountdto.AccountRequestDto;
import com.securebank.dto.accountdto.AccountResponseDto;
import com.securebank.entity.Account;

public interface AccountMapper {

	AccountResponseDto entityToDto(Account account);
	Account dtoToEntity(AccountRequestDto dto); 
	
}
