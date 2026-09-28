package com.securebank.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.securebank.entity.Account;
import com.securebank.entity.Beneficiary;
import com.securebank.entity.User;
import com.securebank.repository.BeneficiaryRepo;
import com.securebank.service.BeneficiaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService{

	private final BeneficiaryRepo beneficiaryRepo;
	
	@Override
	public void saveBeneficiary(User user, Account beneficiaryAccount) {
		
		boolean exists = beneficiaryRepo.existsByUserIdAndBeneficiaryAccountId
				(user.getId(), beneficiaryAccount.getId());
		
		if(exists) {
			return;
		}
		
		Beneficiary beneficiary = new Beneficiary();
		
		beneficiary.setUser(user);
		beneficiary.setBeneficiaryAccount(beneficiaryAccount);
		beneficiary.setNickname(beneficiaryAccount.getUser().getUsername());
		
		beneficiaryRepo.save(beneficiary);
		
	}

}
