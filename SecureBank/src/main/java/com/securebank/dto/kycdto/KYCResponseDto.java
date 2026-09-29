package com.securebank.dto.kycdto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.securebank.enums.Gender;
import com.securebank.enums.KYCStatus;

import lombok.Data;

@Data
public class KYCResponseDto {

	    private Integer id;
	    private Integer userId;
	    private String username;
	    private String email;
	    private String aadharNumber;
	    private String panCardNumber;
	    private LocalDate dateOfBirth;
	    private String fatherName;
	    private Gender gender;
	    private Integer age;
	    private String address;
	    private String city;
	    private String state;
	    private Integer pincode;
	    private LocalDateTime submittedAt;
	    private KYCStatus status;

}
