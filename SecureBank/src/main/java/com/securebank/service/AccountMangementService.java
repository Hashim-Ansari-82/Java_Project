package com.securebank.service;

public interface AccountMangementService {

	String activateAccount(Integer accountId);
	String rejectAccount(Integer accountId);
	String blockAccount(Integer accountId);
	String unblockAccount(Integer accountId);
}
