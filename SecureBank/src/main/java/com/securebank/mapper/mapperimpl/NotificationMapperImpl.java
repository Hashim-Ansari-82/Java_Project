package com.securebank.mapper.mapperimpl;

import org.springframework.stereotype.Component;

import com.securebank.dto.notificationdto.NotificationRequestDto;
import com.securebank.dto.notificationdto.NotificationResponseDto;
import com.securebank.entity.Notification;
import com.securebank.mapper.NotificationMapper;

@Component
public class NotificationMapperImpl implements NotificationMapper{

	@Override
	public NotificationResponseDto entityToDto(Notification notification) {
		
		NotificationResponseDto dto = new NotificationResponseDto();
		
		dto.setId(notification.getId());
		dto.setMessage(notification.getMessage());
		dto.setTitle(notification.getTitle());
		dto.setType(notification.getType());
		dto.setCreatedAt(notification.getCreatedAt());
		
		return dto;
	}

	@Override
	public Notification dtoToEntity(NotificationRequestDto dto) {
		 
		Notification notifications = new Notification();
		
		notifications.setMessage(dto.getMessage());
		notifications.setType(dto.getType());
		notifications.setTitle(dto.getTitle());
		
		return notifications;
	}

	
}
