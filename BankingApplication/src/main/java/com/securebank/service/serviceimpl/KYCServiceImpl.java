package com.securebank.service.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securebank.dto.kycdto.KYCRejectionRequestDto;
import com.securebank.dto.kycdto.KYCRejectionResponseDto;
import com.securebank.dto.kycdto.KYCRequestDto;
import com.securebank.dto.kycdto.KYCResponseDto;
import com.securebank.entity.KYC;
import com.securebank.entity.User;
import com.securebank.enums.AccountStatus;
import com.securebank.enums.KYCStatus;
import com.securebank.enums.MessageType;
import com.securebank.enums.RoleName;
import com.securebank.exception.DuplicateResourceException;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.mapper.KYCMapper;
import com.securebank.repository.KYCRepository;
import com.securebank.repository.UserRepo;
import com.securebank.service.KYCService;
import com.securebank.service.NotificationService;
import com.securebank.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KYCServiceImpl implements KYCService {

	private final UserRepo userRepo;
	private final KYCMapper kycMapper;
	private final KYCRepository kycRepo;
	private final PermissionService permissionService;
	private final NotificationService notificationService;

	@Override
	@Transactional
	public KYCResponseDto submitKYCForm(KYCRequestDto dto, Authentication authentication) {

		String email = authentication.getName();

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with this email " + email));

		if (!user.getRoles().contains(RoleName.USER)) {
			throw new AccessDeniedException("Only User Can Submit KYC Form");
		}

		permissionService.checkUserActive(user);

		if (user.getAccounts() == null || user.getAccounts().isEmpty()) {
			throw new IllegalStateException("First open the account and then submit KYC form");
		}
		
		 boolean hasActiveAccount = user.getAccounts().stream()
		            .anyMatch(account ->
		                    account.getStatus() == AccountStatus.ACTIVE);

		    if (!hasActiveAccount) {
		        throw new IllegalStateException(
		                "You must have an active account to submit KYC.");
		    }

		KYC latestKyc = kycRepo.findTopByUser_IdOrderByIdDesc(user.getId()).orElse(null);

		if (latestKyc != null && latestKyc.getStatus() != KYCStatus.REJECTED) {
			throw new IllegalStateException("You can submit KYC only if your previous KYC was rejected.");
		}

		// Aadhaar/PAN duplicate check only for first submission
		if (latestKyc == null) {
			if (kycRepo.existsByAadharNumber(dto.aadharNumber())
					|| kycRepo.existsByPanCardNumber(dto.panCardNumber())) {
				throw new DuplicateResourceException("Aadhaar or PAN already exists.");
			}
		}

		KYC kyc = kycMapper.dtoToEntity(dto);

		int age = Period.between(kyc.getDateOfBirth(), LocalDate.now()).getYears();

		kyc.setAge(age);
		kyc.setStatus(KYCStatus.PENDING);
		kyc.setUser(user);
		kyc.setVerifiedAt(LocalDateTime.now());

		KYC submitKyc = kycRepo.save(kyc);
		
		notificationService.createNotification(
		        user,
		        "KYC Submitted",
		        MessageType.SYSTEM,
		        "Your KYC has been submitted for verification."
		);

		return kycMapper.entityToDto(submitKyc, user);
	}

	@Override
	@Transactional(readOnly = true)
	public KYCRejectionResponseDto checkMyKyc(String email) {

		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with this email"));

		KYC latestKyc = kycRepo.findTopByUser_IdOrderByIdDesc(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("KYC not submitted by this user"));

		if (latestKyc.getStatus() != KYCStatus.REJECTED) {
			throw new IllegalStateException("Your KYC is not rejected. Current status: " + latestKyc.getStatus());
		}
		
		latestKyc.setVerifiedAt(LocalDateTime.now());

		KYCRejectionResponseDto rejectionDto = kycMapper.rejectionDto(latestKyc, user);

		return rejectionDto;
	}

	@Override
	public String rejectKYC(Integer kycId, KYCRejectionRequestDto dto, String managerEmail) {

		KYC kyc = kycRepo.findById(kycId)
				.orElseThrow(() -> new ResourceNotFoundException("KYC not found on this id" + kycId));

		User user = userRepo.findByEmail(managerEmail)
				.orElseThrow(() -> new ResourceNotFoundException("No found any Manager on this email"));

		permissionService.checkManagerActive(user);

		kyc.setRejectionReason(dto.getRejectionReason());
		kyc.setStatus(KYCStatus.REJECTED);
        kyc.setReviewedBy(user.getUsername());
		kyc.setVerifiedAt(LocalDateTime.now());

		kycRepo.save(kyc);

		return "Kyc Rejected this user ...! ";
	}

	@Override
	public KYCResponseDto approveKYC(Integer kycId,Authentication authentication) {

		String name = authentication.getName();
		
		User manager = userRepo.findByEmail(name).orElseThrow(() -> 
		new ResourceNotFoundException("Manager Not found On this email " + name));
		
		KYC kyc = kycRepo.findById(kycId)
				.orElseThrow(() -> new ResourceNotFoundException("KYC not found on this id" + kycId));

		if (kyc.getStatus() != KYCStatus.PENDING) {
			throw new IllegalStateException("Only Pending Kyc Can be Approved");
		}

		kyc.setStatus(KYCStatus.APPROVED);
		kyc.setVerifiedAt(LocalDateTime.now());
		kyc.setReviewedBy(manager.getUsername());

		KYC varefied = kycRepo.save(kyc);

		return kycMapper.entityToDto(varefied, kyc.getUser());
	}

	@Override
	public List<KYCResponseDto> getAllPendingKYC() {

		List<KYCResponseDto> list = kycRepo.findByStatus(KYCStatus.PENDING).stream().map(kyc -> {
			User user = kycRepo.findUserByKycId(kyc.getId())
					.orElseThrow(() -> new ResourceNotFoundException("User not found for KYC id " + kyc.getId()));

			return kycMapper.entityToDto(kyc, user);
		}).toList();

		return list;
	}

	@Override
	public List<KYCResponseDto> getAllRejectedKYC() {

		List<KYCResponseDto> list = kycRepo.findByStatus(KYCStatus.REJECTED).stream().map(kyc -> {
			User user = kycRepo.findUserByKycId(kyc.getId())
					.orElseThrow(() -> new ResourceNotFoundException("User not found for KYC id " + kyc.getId()));

			return kycMapper.entityToDto(kyc, user);
		}).toList();

		return list;
	}

}
