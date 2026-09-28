package com.securebank.security;

import com.securebank.dto.logindto.LoginRequestDto;
import com.securebank.dto.logindto.LoginResponseDto;
import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.userdto.UsersRegisterRequestDto;

public interface AuthService {

	LoginResponseDto login(LoginRequestDto dto);
	String userRegister(UsersRegisterRequestDto dto);
	String managerRegister(ManagerRequestDto dto);
}
