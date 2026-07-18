package com.hospitalmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmangement.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{

}
