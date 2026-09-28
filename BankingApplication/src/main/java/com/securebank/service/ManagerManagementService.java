package com.securebank.service;

public interface ManagerManagementService {

	String approveManager(Integer userId);
	String rejectManager(Integer userId);
	String blockManager(Integer userId);
	String unblockManager(Integer userId);
	String inactiveManager(Integer userId);
}
