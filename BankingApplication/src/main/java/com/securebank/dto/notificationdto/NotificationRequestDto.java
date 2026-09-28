package com.securebank.dto.notificationdto;

import com.securebank.enums.MessageType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class NotificationRequestDto {

	@NotBlank(message = "Title must be required")
    private String title;
	@NotBlank(message = "Message must be required")
    private String message;
	@NotNull(message = "Message type is required")
    private MessageType type;
	
}