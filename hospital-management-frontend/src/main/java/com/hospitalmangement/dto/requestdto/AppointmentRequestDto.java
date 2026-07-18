package com.hospitalmangement.dto.requestdto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentRequestDto {

	@NotNull(message = "Id is Required")
	private Integer id;
	@NotBlank(message = "Time is Required")
	private LocalTime time;
	@NotBlank(message = "Date is Required")
	@FutureOrPresent(message = "Date can not be in the past")
	private LocalDate date;
	@NotBlank(message = "Status is Required")
	private String status;
	
	@NotNull(message = "Required doctor Id")
	private Integer doctorId;
	@NotNull(message = "Required Patient Id")
	private Integer patientId;
}
