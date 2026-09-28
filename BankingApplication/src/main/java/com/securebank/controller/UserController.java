package com.securebank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.dto.userdto.ChangePasswordRequestDto;
import com.securebank.dto.userdto.UsersRegisterResponseDto;
import com.securebank.dto.userdto.UsersUpdateResponseDto;
import com.securebank.dto.userdto.UserUpdateRequestDto;
import com.securebank.enums.RoleName;
import com.securebank.service.UserManagementService;
import com.securebank.service.UsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UsersService userService;
	private final UserManagementService userManagementService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getMyProfile")
	public ResponseEntity<UsersRegisterResponseDto> getMyProfile(Authentication authentication) {

		String email = authentication.getName();

		UsersRegisterResponseDto user = userService.getByEmail(email);

		return ResponseEntity.ok(user);
	}

	@PutMapping("/updateUserProfile")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public ResponseEntity<UsersUpdateResponseDto> updateMyProfile(Authentication authentication ,@Valid @RequestBody UserUpdateRequestDto dto) {
		
       String email = authentication.getName();

		UsersUpdateResponseDto updateById = userService.updateByEmail(email, dto);

		return ResponseEntity.ok().body(updateById);
	}

	@PutMapping("/change-password")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> changePassword(Authentication authentication,
			@Valid @RequestBody ChangePasswordRequestDto dto) {

		String email = authentication.getName();

		userService.changePassword(email, dto);

		return ResponseEntity.ok("Password changed successfully");
	}

	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<List<UsersRegisterResponseDto>> getAllUsers(Authentication authentication) {

		String email = authentication.getName();

		List<UsersRegisterResponseDto> list = userService.getAllUsers(email);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/pending/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<List<UsersRegisterResponseDto>> getAllPendingUsers(Authentication authentication) {

		String email = authentication.getName();

		List<UsersRegisterResponseDto> list = userService.getPendingUsers(email);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/rejected/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<List<UsersRegisterResponseDto>> getAllRejectedUsers(Authentication authentication) {

		String email = authentication.getName();

		List<UsersRegisterResponseDto> list = userService.getRejectedUsers(email);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/blocked/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<List<UsersRegisterResponseDto>> getAllBlockedUsers(Authentication authentication) {

		String email = authentication.getName();

		List<UsersRegisterResponseDto> list = userService.getBlockedUsers(email);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/unblocked/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<List<UsersRegisterResponseDto>> getAllUnblockedUsers(Authentication authentication) {

		String email = authentication.getName();

		List<UsersRegisterResponseDto> list = userService.getUnblockedUsers(email);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/inactive/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<List<UsersRegisterResponseDto>> getAllInactiveUsers(Authentication authentication) {

		String email = authentication.getName();

		List<UsersRegisterResponseDto> list = userService.getInactiveUsers(email);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/getUserById/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<UsersRegisterResponseDto> getUserById(@PathVariable Integer id) {

		UsersRegisterResponseDto responseDto = userService.getById(id);

		return ResponseEntity.ok().body(responseDto);
	}

	@PatchMapping("/userDeactivated/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	public ResponseEntity<String> closeUser(@PathVariable Integer id) {

		userService.deactivateUser(id);

		return ResponseEntity.ok().body("User Deactivated...!");
	}

	@PatchMapping("/approve/{userId}/user")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<String> approveUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(userManagementService.approveUser(userId));
	}

	@PatchMapping("/reject/{userId}/user")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<String> rejectUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(userManagementService.rejectUser(userId));
	}

	@PatchMapping("/unblock/{userId}/user")
	public ResponseEntity<String> blockUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(userManagementService.blockUser(userId));

	}

	@PatchMapping("/block/{userId}/user")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<String> unblockUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(userManagementService.unblockUser(userId));

	}

	@PatchMapping("/changeUserRole/{id}")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<UsersUpdateResponseDto> changeUserRole(@PathVariable Integer id, @RequestBody RoleName role) {

		UsersUpdateResponseDto userRole = userService.changeUserRole(id, role);

		return ResponseEntity.ok().body(userRole);
	}

}