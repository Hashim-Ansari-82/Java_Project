package com.securebank.dto.transactiondto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequestDto {

	@NotBlank(message = "Must be Required")
    private BigDecimal amount;

	@NotNull(message = "Receiver Account required")
    private Integer receiverAccountId;
	
    private String remark;
}
