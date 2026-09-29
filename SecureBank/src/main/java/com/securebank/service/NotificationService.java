package com.securebank.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.securebank.dto.notificationdto.NotificationResponseDto;
import com.securebank.entity.User;
import com.securebank.enums.MessageType;

public interface NotificationService {

	void createNotification(User user,String title,MessageType type ,String message);
	List<NotificationResponseDto> getUserNotifications(Authentication authentication);
}
