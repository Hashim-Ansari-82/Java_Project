package com.securebank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.dto.logindto.LoginRequestDto;
import com.securebank.dto.logindto.LoginResponseDto;
import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.userdto.UsersRegisterRequestDto;
import com.securebank.security.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
 
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
		
		LoginResponseDto login = authService.login(dto);
		return ResponseEntity.ok().body(login);
	}
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> saveUser(@Valid @RequestBody UsersRegisterRequestDto dto) {
		 
		String register = authService.userRegister(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(register);
	}
	
	@PostMapping("/registerManager")
	public ResponseEntity<String> saveManager(@Valid @RequestBody ManagerRequestDto dto) {
		
		String register = authService.managerRegister(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(register);
	}
}
