package com.hospitalmangement.dto.responsedto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;


@Data
public class AppointmentResponseDto {


    private Integer id;

    private LocalTime time;

    private LocalDate date;

    private String status;


    private Integer doctorId;

    private String doctorName;


    private Integer patientId;

    private String patientName;

}