package com.hospitalmangement.mapper;


import org.springframework.stereotype.Component;

import com.hospitalmangement.dto.requestdto.AppointmentRequestDto;
import com.hospitalmangement.dto.responsedto.AppointmentResponseDto;
import com.hospitalmangement.entity.Appointment;


@Component
public class AppointmentMapper {



    public Appointment dtoToEntity(
            AppointmentRequestDto dto) {


        Appointment appointment = new Appointment();


        appointment.setDate(dto.getDate());

        appointment.setTime(dto.getTime());

        appointment.setStatus(dto.getStatus());


        return appointment;

    }





    public AppointmentResponseDto entityToDto(
            Appointment appointment) {


        AppointmentResponseDto dto =
                new AppointmentResponseDto();


        dto.setId(appointment.getId());

        dto.setDate(appointment.getDate());

        dto.setTime(appointment.getTime());

        dto.setStatus(appointment.getStatus());



        if(appointment.getDoctor()!=null){

            dto.setDoctorId(
                appointment.getDoctor().getId()
            );

            dto.setDoctorName(
                appointment.getDoctor().getName()
            );

        }



        if(appointment.getPatient()!=null){

            dto.setPatientId(
                appointment.getPatient().getId()
            );


            dto.setPatientName(
                appointment.getPatient().getName()
            );

        }


        return dto;

    }

}