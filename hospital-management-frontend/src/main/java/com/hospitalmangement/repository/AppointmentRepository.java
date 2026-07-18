package com.hospitalmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospitalmangement.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{}
