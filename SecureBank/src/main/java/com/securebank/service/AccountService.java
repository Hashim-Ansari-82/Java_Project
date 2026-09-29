package com.securebank.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.securebank.dto.accountdto.AccountRequestDto;
import com.securebank.dto.accountdto.AccountResponseDto;
import com.securebank.enums.AccountType;

public interface AccountService {

	AccountResponseDto createAccount(AccountRequestDto dto,Authentication authentication);
	AccountResponseDto getById(Integer id);
	void closeAccount(AccountType accountType,Authentication authentication);
	List<AccountResponseDto> getMyAccount(String email);
	List<AccountResponseDto> getAllAccount(String email);
	List<AccountResponseDto> getAllPendingAccount(String email);
	List<AccountResponseDto> getAllBlockedAccount(String email);
	List<AccountResponseDto> getAllUnblockedAccount(String email);
	List<AccountResponseDto> getAllCloseAccount(String email);
	List<AccountResponseDto> getAllDormantAccount(String email);
	
}
