package com.securebank.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.securebank.enums.Gender;
import com.securebank.enums.KYCRejectionReason;
import com.securebank.enums.KYCStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class KYC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = false)
    private String aadharNumber;

    @Column(nullable = false , unique =  false)
    private String panCardNumber;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private Integer pincode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private KYCStatus status;
    
    @CreationTimestamp
    private LocalDateTime submittedAt;

    private LocalDateTime verifiedAt;
    
    @Enumerated(EnumType.STRING)
    private KYCRejectionReason rejectionReason;

    private String rejectionRemarks;

    private LocalDateTime reviewedAt;

    private String reviewedBy;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}