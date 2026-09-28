package com.securebank.mapper.mapperimpl;

import org.springframework.stereotype.Component;

import com.securebank.dto.accountdto.AccountRequestDto;
import com.securebank.dto.accountdto.AccountResponseDto;
import com.securebank.entity.Account;
import com.securebank.mapper.AccountMapper;

@Component
public class AccountMapperImpl implements AccountMapper {

	@Override
	public AccountResponseDto entityToDto(Account account) {
		AccountResponseDto dto = 
				new AccountResponseDto(
				account.getId(),
				account.getUser().getId(),
				account.getUser().getUsername(),
				account.getUser().getEmail(),
				account.getAccountNumber(),
				account.getCustomerId(),
				account.getAccountType(),
				account.getBalance(),
				account.getStatus(),
				account.getOpenedAt(),
				account.getIFSCCode());
		
		return dto;
	}
	@Override
	public Account dtoToEntity(AccountRequestDto dto) {
		
		Account account = new Account();
		
		account.setAccountType(dto.accountType());
		account.setBalance(dto.balance());
		
		return account;
	}

}
