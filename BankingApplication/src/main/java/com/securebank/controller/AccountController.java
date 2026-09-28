package com.securebank.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.dto.accountdto.AccountRequestDto;
import com.securebank.dto.accountdto.AccountResponseDto;
import com.securebank.enums.AccountType;
import com.securebank.service.AccountMangementService;
import com.securebank.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;
	private final AccountMangementService managementService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/createAccount")
	public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequestDto dto,
			Authentication authentication) {

		System.out.println("Account created Method is running");

		AccountResponseDto account = accountService.createAccount(dto, authentication);
		return ResponseEntity.status(HttpStatus.CREATED).body(account);
	}

	@GetMapping("/getMyAccount")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<AccountResponseDto>> getMyAccount(Authentication authentication) {

		String email = authentication.getName();

		List<AccountResponseDto> myAccount = accountService.getMyAccount(email);

		return ResponseEntity.status(HttpStatus.OK).body(myAccount);
	}

	@PatchMapping("/closeMyAccount/{accountType}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> closeMyAccount(@PathVariable AccountType accountType, Authentication authentication) {

		accountService.closeAccount(accountType, authentication);

		return ResponseEntity.ok().body("Account closed success");
	}

	@GetMapping("/getAllAccount")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<List<AccountResponseDto>> getAllAccount(Authentication authentication) {

		String email = authentication.getName();

		List<AccountResponseDto> responseDto = accountService.getAllAccount(email);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/getAllPendingAccount")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<List<AccountResponseDto>> getAllInactiveAccount(Authentication authentication) {

		String email = authentication.getName();

		List<AccountResponseDto> responseDto = accountService.getAllPendingAccount(email);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/getAllBlockedAccount")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<List<AccountResponseDto>> getAllBlockedAccount(Authentication authentication) {

		String email = authentication.getName();

		List<AccountResponseDto> responseDto = accountService.getAllBlockedAccount(email);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/getAllUnblockedAccount")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<List<AccountResponseDto>> getAllUublockedAccount(Authentication authentication) {

		String email = authentication.getName();

		List<AccountResponseDto> responseDto = accountService.getAllUnblockedAccount(email);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/getAllCloseAccount")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<List<AccountResponseDto>> getAllCloseAccount(Authentication authentication) {

		String email = authentication.getName();

		List<AccountResponseDto> responseDto = accountService.getAllCloseAccount(email);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/getAllDormantAccount")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<List<AccountResponseDto>> getAllDormantAccount(Authentication authentication) {

		String email = authentication.getName();

		List<AccountResponseDto> responseDto = accountService.getAllDormantAccount(email);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@PatchMapping("/activateAccount/{accountId}")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<String> activateAccount(@PathVariable Integer accountId) {

		String activateAccount = managementService.activateAccount(accountId);
		return ResponseEntity.ok().body(activateAccount);

	}

	@PatchMapping("/rejectAccount/{accountId}")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<String> rejectAccount(@PathVariable Integer accountId) {

		String rejectAccount = managementService.rejectAccount(accountId);
		return ResponseEntity.ok().body(rejectAccount);
	}

	@PatchMapping("/blockAccount/{accountId}")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
	public ResponseEntity<String> blockAccount(@PathVariable Integer accountId) {

		String blockAccount = managementService.blockAccount(accountId);
		return ResponseEntity.ok().body(blockAccount);
	}

}
