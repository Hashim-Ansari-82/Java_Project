package com.securebank.service;

import java.util.List;

import com.securebank.dto.userdto.ChangePasswordRequestDto;
import com.securebank.dto.userdto.UsersRegisterResponseDto;
import com.securebank.dto.userdto.UsersUpdateResponseDto;
import com.securebank.dto.userdto.UserUpdateRequestDto;
import com.securebank.enums.RoleName;

public interface UsersService {

	List<UsersRegisterResponseDto> getAllUsers(String email);
	UsersRegisterResponseDto getById(Integer id);
	void deleteByEmail(String email);
	void deactivateUser(Integer id);
	UsersUpdateResponseDto updateByEmail(String email, UserUpdateRequestDto dto);
	UsersUpdateResponseDto changeUserRole(Integer id, RoleName role);
	UsersRegisterResponseDto getByEmail(String email);
	void changePassword(String email,ChangePasswordRequestDto dto);
	List<UsersRegisterResponseDto> getPendingUsers(String email);
	List<UsersRegisterResponseDto> getRejectedUsers(String email);
	List<UsersRegisterResponseDto> getBlockedUsers(String email);
	List<UsersRegisterResponseDto> getInactiveUsers(String email);
	List<UsersRegisterResponseDto> getUnblockedUsers(String email);
 
}
