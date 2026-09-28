package com.securebank.dto.accountdto;

import java.math.BigDecimal;

import com.securebank.enums.AccountType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AccountRequestDto
        (
        	@NotNull(message="Account type is required") 
        	AccountType accountType,
		    @NotNull(message="Amount Must be Required")
        	@Positive(message = "Amount must be positive")
            BigDecimal balance
         ) {

}