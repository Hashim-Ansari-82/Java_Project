package com.securebank.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.securebank.entity.Account;
import com.securebank.enums.AccountStatus;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.repository.AccountRepo;
import com.securebank.service.AccountMangementService;
import com.securebank.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountManagementServiceImpl implements AccountMangementService{

	private final AccountRepo accountRepo;
	private final PermissionService permissionService;
	
	@Override
	public String activateAccount(Integer accountId) {
		
		Account account = accountRepo.findById(accountId).orElseThrow(()  ->
		new ResourceNotFoundException("No any type of account on this id "+accountId));
		
		permissionService.checkManagerActive(account.getUser());
		
		if(account.getStatus() != AccountStatus.UNBLOCKED &&
				account.getStatus() != AccountStatus.PENDING &&
				account.getStatus() != AccountStatus.DORMANT) {
			throw new ResourceNotFoundException("only Unblocked Pending and Dormant account can be Activate");
		}
		
		account.setStatus(AccountStatus.ACTIVE);
		
		accountRepo.save(account);
		
		return "Account Activated Successfully....! ";
	}

	@Override
	public String rejectAccount(Integer accountId) {
		
		Account account = accountRepo.findById(accountId).orElseThrow(()  ->
		new ResourceNotFoundException("No any type of account on this id "+accountId));
		
		permissionService.checkManagerActive(account.getUser());
		
		if(account.getStatus() != AccountStatus.PENDING) {
			throw new ResourceNotFoundException("Only Pending Account can be Rejected");
		}
		
		account.setStatus(AccountStatus.REJECTED);
		
		accountRepo.save(account);
		
		return "Account Rejected Successfully....! ";
	}

	@Override
	public String blockAccount(Integer accountId) {

		Account account = accountRepo.findById(accountId).orElseThrow(()  ->
		new ResourceNotFoundException("Only Active Account Allowed"));
		
		permissionService.checkManagerActive(account.getUser());
		
		if(account.getStatus() != AccountStatus.ACTIVE){
			throw new ResourceNotFoundException("Only Active Account can be Blocked ");
		}
		
		account.setStatus(AccountStatus.BLOCKED);
		
		accountRepo.save(account);
		
		return "Account Blocked Successfully....! ";
	}

	@Override
	public String unblockAccount(Integer accountId) {
		
		Account account = accountRepo.findById(accountId).orElseThrow(()  ->
		new ResourceNotFoundException("Only Active Account Allowed"));
		
		permissionService.checkManagerActive(account.getUser());
		
		if(account.getStatus() != AccountStatus.BLOCKED){
			throw new ResourceNotFoundException("Only Blocked Account can be Unblocked");
		}
		
		account.setStatus(AccountStatus.UNBLOCKED);
		
		accountRepo.save(account);
		
		return "Account Unblocked Successfully....! ";
	}
	

}
