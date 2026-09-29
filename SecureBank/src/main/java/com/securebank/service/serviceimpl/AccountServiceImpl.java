package com.securebank.service.serviceimpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.securebank.dto.accountdto.AccountRequestDto;
import com.securebank.dto.accountdto.AccountResponseDto;
import com.securebank.entity.Account;
import com.securebank.entity.User;
import com.securebank.enums.AccountStatus;
import com.securebank.enums.AccountType;
import com.securebank.enums.MessageType;
import com.securebank.enums.RoleName;
import com.securebank.exception.DuplicateResourceException;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.mapper.AccountMapper;
import com.securebank.repository.AccountRepo;
import com.securebank.repository.UserRepo;
import com.securebank.service.AccountService;
import com.securebank.service.NotificationService;
import com.securebank.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepo accountRepo;
	private final UserRepo userRepo;
	private final AccountMapper accountMapper;
	private final PermissionService permissionService;
	private final NotificationService notificationService;

	@Override
	public AccountResponseDto createAccount(AccountRequestDto dto, Authentication authentication) {

		String email = authentication.getName();

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found this id " + email));

		permissionService.checkUserActive(user);

		Account account = accountMapper.dtoToEntity(dto);
		account.setCustomerName(user.getUsername());
		account.setCoustomerEmail(user.getEmail());
		account.setAccountNumber(generateAccountNumber());
		account.setCustomerId(generateCustomerId());
		account.setIFSCCode("SBIN00000032");
		account.setStatus(AccountStatus.PENDING);
		account.setUser(user);

		if (accountRepo.existsByCustomerId(account.getCustomerId())) {
			throw new DuplicateResourceException("CustomerId is already exists " + account.getCustomerId());
		}

		else if (accountRepo.existsByAccountNumber(account.getAccountNumber())) {
			throw new DuplicateResourceException("Account number is already exists " + account.getAccountNumber());
		}

		else if (accountRepo.existsByUser_IdAndAccountType(user.getId(), dto.accountType())) {
			throw new DuplicateResourceException(
					"User " + user.getId() + " is already exists on this account Type is " + dto.accountType());
		}

		Account respAccount = accountRepo.save(account);
		
		notificationService.createNotification(
		        user,
		        "Account Request Submitted",
		        MessageType.ACCOUNT,
		        "Your account opening request has been submitted."
		);

		return accountMapper.entityToDto(respAccount);
	}

	@Override
	public AccountResponseDto getById(Integer id) {

		Account account = accountRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found on this id is " + id));
		AccountResponseDto responseDto = accountMapper.entityToDto(account);

		return responseDto;
	}

	@Override
	public void closeAccount(AccountType accountType, Authentication authentication) {

		String email = authentication.getName();

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this email " + email));

		permissionService.checkUserActive(user);

		Account account = accountRepo.findByUserAndAccountType(user, accountType)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found on this is user " + email));

		permissionService.checkAccountActive(account);

		if (account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
			throw new IllegalStateException("Account balance must be zero");
		}
		if (account.getStatus() == AccountStatus.CLOSE) {
			throw new IllegalStateException("Account is already closed");
		}

		account.setStatus(AccountStatus.CLOSE);

		accountRepo.save(account);

	}

	private String generateAccountNumber() {

		Random random = new Random();

		String accountNumber;

		do {
			accountNumber = String.valueOf(100000000000L + random.nextLong(900000000000L));
		} while (accountRepo.existsByAccountNumber(accountNumber));

		return accountNumber;
	}

	private String generateCustomerId() {

		Random random = new Random();

		String customerId;

		do {
			customerId = String.valueOf(10000000L + random.nextLong(90000000L));
		} while (accountRepo.existsByCustomerId(customerId));

		return customerId;
	}

	@Override
	public List<AccountResponseDto> getMyAccount(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this email " + email));

		permissionService.checkUserActive(user);

		List<AccountResponseDto> list = accountRepo.findByUser_Email(email).stream().map(accountMapper::entityToDto)
				.toList();

		return list;
	}

	@Override
	public List<AccountResponseDto> getAllAccount(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this email " + email));

		if (user.getRoles().contains(RoleName.MANAGER)) {
			permissionService.checkManagerActive(user);
		}

		List<AccountResponseDto> list = accountRepo.findByStatus(AccountStatus.ACTIVE).stream()
				.map(accountMapper::entityToDto).toList();

		return list;
	}

	@Override
	public List<AccountResponseDto> getAllPendingAccount(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this emial " + email));

		if (user.getRoles().contains(RoleName.MANAGER)) {
			permissionService.checkManagerActive(user);
		}

		List<AccountResponseDto> list = accountRepo.findByStatus(AccountStatus.PENDING).stream()
				.map(accountMapper::entityToDto).toList();

		return list;
	}

	@Override
	public List<AccountResponseDto> getAllBlockedAccount(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this emial " + email));

		if (user.getRoles().contains(RoleName.MANAGER)) {
			permissionService.checkManagerActive(user);
		}

		List<AccountResponseDto> list = accountRepo.findByStatus(AccountStatus.BLOCKED).stream()
				.map(accountMapper::entityToDto).toList();

		return list;
	}

	@Override
	public List<AccountResponseDto> getAllUnblockedAccount(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this emial " + email));

		if (user.getRoles().contains(RoleName.MANAGER)) {
			permissionService.checkManagerActive(user);
		}

		List<AccountResponseDto> list = accountRepo.findByStatus(AccountStatus.UNBLOCKED).stream()
				.map(accountMapper::entityToDto).toList();

		return list;
	}

	@Override
	public List<AccountResponseDto> getAllCloseAccount(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this emial " + email));

		if (user.getRoles().contains(RoleName.MANAGER)) {
			permissionService.checkManagerActive(user);
		}

		List<AccountResponseDto> list = accountRepo.findByStatus(AccountStatus.CLOSE).stream()
				.map(accountMapper::entityToDto).toList();

		return list;
	}

	@Override
	public List<AccountResponseDto> getAllDormantAccount(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on this emial " + email));

		if (user.getRoles().contains(RoleName.MANAGER)) {
			permissionService.checkManagerActive(user);
		}

		List<AccountResponseDto> list = accountRepo.findByStatus(AccountStatus.DORMANT).stream()
				.map(accountMapper::entityToDto).toList();

		return list;
	}

}
