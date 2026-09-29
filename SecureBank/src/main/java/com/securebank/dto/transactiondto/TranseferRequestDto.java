package com.securebank.dto.transactiondto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TranseferRequestDto {

	@NotBlank(message = "Must be Required")
    private BigDecimal amount;

	@NotBlank(message = "Receiver Account required")
    private String receiverAccountNumber;
	
    private String remark;
}
