package com.securebank.mapper;

import com.securebank.dto.notificationdto.NotificationRequestDto;
import com.securebank.dto.notificationdto.NotificationResponseDto;
import com.securebank.entity.Notification;

public interface NotificationMapper {

	NotificationResponseDto entityToDto(Notification notification);
	Notification dtoToEntity(NotificationRequestDto dto);
}
