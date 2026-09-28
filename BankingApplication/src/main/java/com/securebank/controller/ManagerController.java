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

import com.securebank.dto.managerdto.ManagerRequestDto;
import com.securebank.dto.managerdto.ManagerResponseDto;
import com.securebank.dto.managerdto.ManagerUpdateRequestDto;
import com.securebank.dto.managerdto.ManagerUpdateResponseDto;
import com.securebank.enums.RoleName;
import com.securebank.service.ManagerManagementService;
import com.securebank.service.ManagerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor	
public class ManagerController {

	private final ManagerService managerService;
    private final ManagerManagementService managerManagementService;
	
	@GetMapping("/getMyProfile")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<ManagerResponseDto> getMyProfile(Authentication authentication){
		
		String email = authentication.getName();
		
		ManagerResponseDto myProfile = managerService.getMyProfile(email);
		return ResponseEntity.ok().body(myProfile);
	}
	
	@PutMapping("/updateMyProfile")
	@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
	public ResponseEntity<ManagerResponseDto> updateMyProfile(Authentication authentication,
	        @Valid @RequestBody ManagerRequestDto dto) {
 
	    String email = authentication.getName();
	    
	   ManagerResponseDto updateMyProfile = managerService.updateMyProfile(email, dto);
	    
	    return ResponseEntity.ok().body(updateMyProfile);
	}

	@PutMapping("/approveManager/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> approveManager(@PathVariable Integer userId) {

		return ResponseEntity.ok(managerManagementService.approveManager(userId));
	}

	@PutMapping("/rejectedManager/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> rejectManager(@PathVariable Integer userId) {

		return ResponseEntity.ok(managerManagementService.rejectManager(userId));
	}

	@PutMapping("/blockManager/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> blockManager(@PathVariable Integer userId) {

		return ResponseEntity.ok(managerManagementService.blockManager(userId));

	}
	
	@PatchMapping("/unblockManager/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> unblockManager(Integer userId) {
		
		String unblockManager = managerManagementService.unblockManager(userId);
		return ResponseEntity.ok().body(unblockManager);
	}
	
	@PatchMapping("/inactiveManager/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<String> inactiveManager(Integer userId){
		
		String inactiveManager = managerManagementService.inactiveManager(userId);
		return ResponseEntity.ok().body(inactiveManager);
	}
	
	@PatchMapping("/changeManagerRole/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<ManagerResponseDto> changeManagerRole(@PathVariable Integer id,@RequestBody RoleName role) {
		
		ManagerResponseDto managerRole = managerService.changeManagerRole(id, role);
		
		return ResponseEntity.ok().body(managerRole);
	}
	
	@GetMapping("/getAllManager")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<ManagerResponseDto>> getAllManager() {
		
		List<ManagerResponseDto> allManager = managerService.getAllManager();
		
		return ResponseEntity.ok().body(allManager);
	}
	
	@GetMapping("/getManagerById/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<ManagerResponseDto> getManagerById(@PathVariable Integer id){
		
		ManagerResponseDto managerById = managerService.getManagerById(id);
		
		return ResponseEntity.ok().body(managerById);
	}
	
	@PutMapping("/updateManagerProfile/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<ManagerUpdateResponseDto> updateManagerProfile(
			@PathVariable Integer id,
			@Valid @RequestBody ManagerUpdateRequestDto dto){
		
		ManagerUpdateResponseDto updateMyProfileByAdmin = managerService.updateMyProfileByAdmin(id, dto);
	 
		return ResponseEntity.ok().body(updateMyProfileByAdmin);
	}

   @GetMapping("/pendingManager")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
   public ResponseEntity<List<ManagerResponseDto>> getPendingManager(){
		 
		List<ManagerResponseDto> pendingManager = managerService.getPendingManager();
		return ResponseEntity.ok().body(pendingManager);
	}
 
   @GetMapping("/rejectedManager")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<ManagerResponseDto>> getRejectedManager() {

	   List<ManagerResponseDto> rejectedManager = managerService.getRejectedManager();
		return ResponseEntity.ok().body(rejectedManager);
	}

   @GetMapping("/blockedManager")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<ManagerResponseDto>> getBlockedManager() {
	   
	   List<ManagerResponseDto> blockedManager = managerService.getBlockedManager();
	   return ResponseEntity.ok().body(blockedManager);
	}

   @GetMapping("/unblockedManager")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<ManagerResponseDto>> getUnblockedManager() {
		
	   List<ManagerResponseDto> unblockedManager = managerService.getUnblockedManager();
	   
	   return ResponseEntity.ok().body(unblockedManager);
	}

   @GetMapping("/inactiveManager")
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<ManagerResponseDto>> getInactiveManager() {
		
	   List<ManagerResponseDto> inactiveManager = managerService.getInactiveManager();
	   
	   return ResponseEntity.ok().body(inactiveManager);
	}
   
}
