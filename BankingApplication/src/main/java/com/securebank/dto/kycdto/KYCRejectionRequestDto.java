package com.securebank.dto.kycdto;

import com.securebank.enums.KYCRejectionReason;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KYCRejectionRequestDto {

	@NotNull(message = "Rejection reason is required")
	private KYCRejectionReason rejectionReason;

	private String rejectionRemarks;

}
