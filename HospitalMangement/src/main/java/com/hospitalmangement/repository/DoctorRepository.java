package com.hospitalmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmangement.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{}
