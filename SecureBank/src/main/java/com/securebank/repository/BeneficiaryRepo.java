package com.securebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securebank.entity.Beneficiary;

@Repository
public interface BeneficiaryRepo
        extends JpaRepository<Beneficiary, Integer> {

    boolean existsByUserIdAndBeneficiaryAccountId(
            Integer userId,
            Integer accountId
    );
}
