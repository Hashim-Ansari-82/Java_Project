package com.securebank.dto.managerdto;

import java.time.LocalDateTime;
import java.util.Set;

import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerResponseDto {

	private Integer id;
	private String username;
	private String email;
	private String mobile;
	private Set<RoleName> role;
	private UsersStatus status;
	private String address;
	private LocalDateTime createdAt;
}
