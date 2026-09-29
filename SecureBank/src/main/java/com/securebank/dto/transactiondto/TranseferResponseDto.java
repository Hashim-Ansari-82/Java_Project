package com.securebank.dto.transactiondto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.securebank.enums.TransactionStatus;
import com.securebank.enums.TransactionType;

import lombok.Getter;
import lombok.Setter;
 
@Setter
@Getter
public class TranseferResponseDto {

	private Integer id;
	private String transactionReference;
	private BigDecimal amount;
	private TransactionType transactionType;
	private TransactionStatus status;
	private String remark;
	private LocalDateTime createdAt;
	
}
