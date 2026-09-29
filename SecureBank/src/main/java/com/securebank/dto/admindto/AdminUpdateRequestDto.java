package com.securebank.dto.admindto;

import com.securebank.enums.UsersStatus;

public record AdminUpdateRequestDto(

		String username, String email, String mobile, UsersStatus status
) {

}
