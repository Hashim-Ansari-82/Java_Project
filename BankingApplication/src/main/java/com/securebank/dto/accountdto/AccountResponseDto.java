package com.securebank.dto.accountdto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.securebank.enums.AccountStatus;
import com.securebank.enums.AccountType;

public record AccountResponseDto
		(Integer accountId,Integer userId,
		String customerName,String costomerEmail,
		String accountNumber,String customerId,
		AccountType accountType,BigDecimal balance,
		AccountStatus status,LocalDateTime openedAt,
		String IFSCCode) {

}
