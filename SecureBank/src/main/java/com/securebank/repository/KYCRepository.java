package com.securebank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.securebank.entity.KYC;
import com.securebank.entity.User;
import com.securebank.enums.KYCStatus;


@Repository
public interface KYCRepository extends JpaRepository<KYC, Integer> {

	List<KYC> findByStatus(KYCStatus status);
	boolean existsByPanCardNumber(String aadharNumber); 
	boolean existsByAadharNumber(String panCardNumber);
	@Query("SELECT u FROM User u JOIN u.kycs k WHERE k.id = :kycId")
	Optional<User> findUserByKycId(@Param("kycId") Integer kycId);
	Optional<KYC> findTopByUser_IdOrderByIdDesc(Integer userId);
}
