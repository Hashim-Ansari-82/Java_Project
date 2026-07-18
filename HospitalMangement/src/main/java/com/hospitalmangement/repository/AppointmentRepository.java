package com.hospitalmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmangement.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{}
