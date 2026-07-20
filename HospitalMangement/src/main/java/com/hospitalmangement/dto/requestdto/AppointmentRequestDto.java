package com.hospitalmangement.dto.requestdto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AppointmentRequestDto {


    @NotNull(message = "Time is Required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;


    @NotNull(message = "Date is Required")
    @FutureOrPresent(message = "Date cannot be in past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;


    private String status;


    @NotNull(message = "Doctor is Required")
    private Integer doctorId;


    @NotNull(message = "Patient is Required")
    private Integer patientId;

}