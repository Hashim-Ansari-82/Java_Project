package com.securebank.service;

import com.securebank.entity.Account;
import com.securebank.entity.User;

public interface BeneficiaryService {

    void saveBeneficiary(User user, Account beneficiaryAccount);
}