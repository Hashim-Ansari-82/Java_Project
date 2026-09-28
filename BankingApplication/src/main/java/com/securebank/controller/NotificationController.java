package com.securebank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securebank.dto.notificationdto.NotificationResponseDto;
import com.securebank.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;
	
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<NotificationResponseDto>> getUserNotifications(Authentication authentication) { 
 
	    return ResponseEntity.ok(
	            notificationService.getUserNotifications(authentication)
	    );
	}
}
