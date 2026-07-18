package com.hospitalmangement.dto.responsedto;

import lombok.Data;

@Data
public class DoctorResponseDto {

	private Integer id;
	private String name ;
	private String specialization;
	
	private Integer departmentId;
	private String departmentName;
}
