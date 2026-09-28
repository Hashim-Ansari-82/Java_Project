package com.securebank.dto.kycdto;

import java.time.LocalDateTime;

import com.securebank.enums.KYCRejectionReason;
import com.securebank.enums.KYCStatus;

import lombok.Data;

@Data
public class KYCRejectionResponseDto {

	private Integer kycId;
	private Integer userId;
	private String username;
	private String user_Email;
	private KYCStatus status;
	private KYCRejectionReason rejectionReason;
	private String rejectionRemarks;
	private LocalDateTime verifiedAt;
}
