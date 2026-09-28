package com.securebank.dto.userdto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordRequestDto {

	private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
