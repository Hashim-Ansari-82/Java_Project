package com.securebank.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.dto.transactiondto.TransactionRequestDto;
import com.securebank.dto.transactiondto.TransactionResponseDto;
import com.securebank.dto.userdto.UsersRegisterResponseDto;
import com.securebank.service.TransactionService;
import com.securebank.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

	private final TransactionService transactionService;
	private final UsersService userSerivce;
	
	@PostMapping("/transfer")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<TransactionResponseDto> transfer(@RequestBody TransactionRequestDto dto,Authentication authentication) {
		
		String email = authentication.getName();
		
		TransactionResponseDto transfer = transactionService.transfer(email,dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(transfer);
	}
	
	@GetMapping("/getAllTransaction")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public ResponseEntity<List<TransactionResponseDto>> getAllTransaction(){
		 
		List<TransactionResponseDto> list = transactionService.getAllTransaction();
		return ResponseEntity.status(HttpStatus.OK).body(list); 
	}
	
	@GetMapping("/showMyAllTransaction")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<TransactionResponseDto>> showMyAllTransaction(Authentication authentication){
		
		String eamil = authentication.getName();
		
		UsersRegisterResponseDto responseDto = userSerivce.getByEmail(eamil);
		List<TransactionResponseDto> transactionByEmail = transactionService.getTransactionByEmail(responseDto.getEmail());
		
		return ResponseEntity.status(HttpStatus.OK).body(transactionByEmail); 
	}
 
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		
		transactionService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	}
}
