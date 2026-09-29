package com.securebank.service;

public interface UserManagementService {

	String approveUser(Integer userId);
	String rejectUser(Integer userId);
	String blockUser(Integer userId);
	String unblockUser(Integer userId);
}
