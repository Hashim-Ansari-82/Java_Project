package com.securebank.dto.userdto;

import java.time.LocalDate;

import com.securebank.enums.UsersStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersRegisterResponseDto {

    private Integer id;
    private String username;
    private String email;
    private String mobile;
    private String address;
    private UsersStatus status;
    private LocalDate createdAt;
}
