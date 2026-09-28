package com.securebank.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.securebank.entity.Account;
import com.securebank.entity.KYC;
import com.securebank.entity.User;
import com.securebank.enums.AccountStatus;
import com.securebank.enums.KYCStatus;
import com.securebank.enums.UsersStatus;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

	@Override
	public void checkUserActive(User user) {

		if (user.getStatus() != UsersStatus.ACTIVE) {
			throw new ResourceNotFoundException("You are not active you can't perform any operation right now");
		}
	}

	@Override
	public void checkManagerActive(User user) {

		if (user.getStatus() != UsersStatus.ACTIVE) {
			throw new ResourceNotFoundException("You are not active you can't perform any operation right now");
		}
	}

	@Override
	public void checkKycApproved(KYC kyc) {

		if (kyc.getUser().getStatus() != UsersStatus.ACTIVE) {
			throw new ResourceNotFoundException("You are not active you can't perform any operation right now");
		}

		if (kyc.getStatus() != KYCStatus.APPROVED) {
			throw new IllegalStateException("Your KYC is not approved. You cannot perform this operation.");
		}
	}

	@Override
	public void checkAccountActive(Account account) {

		if (account.getStatus() != AccountStatus.ACTIVE) {
			throw new RuntimeException("Your Account currently is not active");
		}
	}
}
