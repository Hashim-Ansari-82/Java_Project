package com.securebank.service;

import com.securebank.entity.Account;
import com.securebank.entity.KYC;
import com.securebank.entity.User;

public interface PermissionService {

	void checkUserActive(User user);

	void checkManagerActive(User user);

	void checkKycApproved(KYC kyc);

	void checkAccountActive(Account account);
}
