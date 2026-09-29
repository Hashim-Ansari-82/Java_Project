package com.securebank.service.serviceimpl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.securebank.dto.notificationdto.NotificationResponseDto;
import com.securebank.entity.Notification;
import com.securebank.entity.User;
import com.securebank.enums.MessageType;
import com.securebank.mapper.NotificationMapper;
import com.securebank.repository.NotificationRepo;
import com.securebank.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepo notiRepo;
	private final NotificationMapper notiMapper;
	
	@Override
	public void createNotification(User user,String title,MessageType type ,String message) {
		
        Notification notification =new  Notification();
		
		notification.setUser(user);
		notification.setTitle(title);
		notification.setType(type);
		notification.setMessage(message);
		notification.setRead(false);
        
		notiRepo.save(notification);
	}

	@Override
	public List<NotificationResponseDto> getUserNotifications(Authentication  authentication) {
		
		String email = authentication.getName();
		
		List<NotificationResponseDto> list = notiRepo.findByUser_IdOrderByCreatedAtDesc(email).
		stream().map(notiMapper::entityToDto).toList();
		
		return list;
	}

}
