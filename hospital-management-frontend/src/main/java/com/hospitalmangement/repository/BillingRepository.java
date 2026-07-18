package com.hospitalmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospitalmangement.entity.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer>{}
