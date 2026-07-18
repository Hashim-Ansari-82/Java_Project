package com.hospitalmangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmangement.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Integer>{}
