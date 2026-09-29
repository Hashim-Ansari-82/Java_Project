package com.securebank.dto.notificationdto;

import java.time.LocalDateTime;

import com.securebank.enums.MessageType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationResponseDto {

    private Integer id;
    private String title;
    private String message;
    private MessageType type;
    private LocalDateTime createdAt;
}
