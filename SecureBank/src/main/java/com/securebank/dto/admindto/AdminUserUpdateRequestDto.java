package com.securebank.dto.admindto;

import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;

public record AdminUserUpdateRequestDto(
		 
		    String username,
		    String email,
		    String mobile,
		    RoleName role,
		    UsersStatus status
		
		){

}
