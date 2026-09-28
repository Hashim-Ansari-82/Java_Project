package com.securebank.dto.userdto;

import java.time.LocalDate;
import java.util.Set;

import com.securebank.enums.RoleName;
import com.securebank.enums.UsersStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter 
public class UsersUpdateResponseDto {


    private Integer id;
    private String username;
    private String email;
    private String mobile;
    private String address;
    private Set<RoleName> role;
    private UsersStatus status;
    private LocalDate updatedAt;
}
