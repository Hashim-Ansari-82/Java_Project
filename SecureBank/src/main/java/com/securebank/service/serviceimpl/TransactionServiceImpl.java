package com.securebank.service.serviceimpl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.securebank.dto.transactiondto.DepositRequestDto;
import com.securebank.dto.transactiondto.DepositResponseDto;
import com.securebank.dto.transactiondto.TranseferRequestDto;
import com.securebank.dto.transactiondto.TranseferResponseDto;
import com.securebank.entity.Account;
import com.securebank.entity.KYC;
import com.securebank.entity.Transaction;
import com.securebank.entity.User;
import com.securebank.enums.AccountStatus;
import com.securebank.enums.MessageType;
import com.securebank.enums.TransactionStatus;
import com.securebank.enums.TransactionType;
import com.securebank.exception.DuplicateResourceException;
import com.securebank.exception.ResourceNotFoundException;
import com.securebank.mapper.TransactionMapper;
import com.securebank.repository.AccountRepo;
import com.securebank.repository.TransactionRepo;
import com.securebank.repository.UserRepo;
import com.securebank.service.BeneficiaryService;
import com.securebank.service.NotificationService;
import com.securebank.service.PermissionService;
import com.securebank.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepo transactionRepo;
	private final TransactionMapper transactionMapper;
	private final AccountRepo accountRepo;
	private final NotificationService notificationService;
	private final BeneficiaryService beneficiaryService;
	private final PermissionService permissionService;
	private final UserRepo userRepo;
	
	@Override
	@Transactional
	public TranseferResponseDto transfer(String email,TranseferRequestDto dto) {
		
		User user = userRepo.findByEmail(email).orElseThrow(()-> 
		new ResourceNotFoundException("User Not found one this email"));
		
		List<KYC> kycs = user.getKycs();

		if (kycs == null || kycs.isEmpty()) {
		    throw new IllegalStateException("KYC not found");
		}

		KYC latestKyc = kycs.stream()
		        .max(Comparator.comparing(KYC::getId))
		        .orElseThrow();

		permissionService.checkKycApproved(latestKyc);
		
		List<Account> accounts = user.getAccounts();
		Account sendAccount = accounts.stream()
		        .filter(account -> account.getStatus() == AccountStatus.ACTIVE)
		        .findFirst()
		        .orElseThrow(() ->
		                new IllegalStateException("Active account not found"));

		String senderAccountNumber = sendAccount.getAccountNumber();
		String receiverAccountNumber = dto.getReceiverAccountNumber();
		BigDecimal amount = dto.getAmount();
		
		
		/* Find Sender Account */
		Account senderAccount = accountRepo.findByAccountNumber(senderAccountNumber).orElseThrow(() ->
		new ResourceNotFoundException("Sender Account not found on this Account number "+senderAccountNumber));
		
		permissionService.checkAccountActive(senderAccount);
		
		/* Find Receiver Account */
		Account receiverAccount = accountRepo.findByAccountNumber(senderAccountNumber).orElseThrow(() ->
		new ResourceNotFoundException("Reciever Account not found on this Account Number "+receiverAccountNumber));
		
		permissionService.checkAccountActive(receiverAccount );
		
		/* Check Both account are same */
		if(senderAccountNumber.equals(senderAccountNumber)) {
			throw new DuplicateResourceException(
					"Sender And Reciever Account can't be Same");	
		}
		
		/* Check amount */
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Amount must be Greater than Zero");
		}
		
		/* Check Balance */
		if(senderAccount.getBalance().compareTo(amount) < 0) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		/* Send amount */
		senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
		
		/* Receiver amount */
		receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
		
		accountRepo.save(senderAccount);
		accountRepo.save(receiverAccount);
		
		Transaction transaction = transactionMapper.dtoToEntity(dto);
		transaction.setSenderAccount(senderAccount);
		transaction.setReceiverAccount(receiverAccount);
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setTransactionReference(generateTransactionReference());
		transaction.setStatus(TransactionStatus.SUCCESS);
		
		Transaction tx = transactionRepo.save(transaction);
		
		notificationService.createNotification(
		        senderAccount.getUser(),
		        "Money Transfer",
		        MessageType.TRANSFER,
		        "₹" + amount + " transferred successfully to account "
		                + receiverAccount.getAccountNumber()
		);
		notificationService.createNotification(
		        receiverAccount.getUser(),
		        "Money Received",
		        MessageType.TRANSFER,
		        "₹" + amount + " received from account "
		                + senderAccount.getAccountNumber()
		);
		
        beneficiaryService.saveBeneficiary(senderAccount.getUser(), receiverAccount);
		
		return transactionMapper.entityToDto(tx);
		
	}
	
	@Override
	@Transactional
	public DepositResponseDto deposit(String email,DepositRequestDto dto) {
		
		User user = userRepo.findByEmail(email).orElseThrow(()-> 
		new ResourceNotFoundException("User Not found one this email"));
		
		List<KYC> kycs = user.getKycs();
		
		if (kycs == null || kycs.isEmpty()) {
			throw new IllegalStateException("KYC not found");
		}
		
		KYC latestKyc = kycs.stream()
				.max(Comparator.comparing(KYC::getId))
				.orElseThrow();
		
		permissionService.checkKycApproved(latestKyc);
		
		List<Account> accounts = user.getAccounts();
		Account sendAccount = accounts.stream()
				.filter(account -> account.getStatus() == AccountStatus.ACTIVE)
				.findFirst()
				.orElseThrow(() ->
				new IllegalStateException("Active account not found"));
		
		String senderAccountNumber = sendAccount.getAccountNumber();
		String receiverAccountNumber = dto.getReceiverAccountNumber();
		BigDecimal amount = dto.getAmount();
		
		
		/* Find Sender Account */
		Account senderAccount = accountRepo.findByAccountNumber(senderAccountNumber).orElseThrow(() ->
		new ResourceNotFoundException("Sender Account not found on this Account number "+senderAccountNumber));
		
		permissionService.checkAccountActive(senderAccount);
		
		/* Find Receiver Account */
		Account receiverAccount = accountRepo.findByAccountNumber(senderAccountNumber).orElseThrow(() ->
		new ResourceNotFoundException("Reciever Account not found on this Account Number "+receiverAccountNumber));
		
		permissionService.checkAccountActive(receiverAccount );
		
		/* Check Both account are same */
		if(senderAccountNumber.equals(senderAccountNumber)) {
			throw new DuplicateResourceException(
					"Sender And Reciever Account can't be Same");	
		}
		
		/* Check amount */
		if(amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Amount must be Greater than Zero");
		}
		
		/* Check Balance */
		if(senderAccount.getBalance().compareTo(amount) < 0) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		/* Send amount */
		senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
		
		/* Receiver amount */
		receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
		
		accountRepo.save(senderAccount);
		accountRepo.save(receiverAccount);
		
		Transaction transaction = transactionMapper.dtoToEntity(dto);
		transaction.setSenderAccount(senderAccount);
		transaction.setReceiverAccount(receiverAccount);
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setTransactionReference(generateTransactionReference());
		transaction.setStatus(TransactionStatus.SUCCESS);
		
		Transaction tx = transactionRepo.save(transaction);
		
		notificationService.createNotification(
				senderAccount.getUser(),
				"Money Transfer",
				MessageType.TRANSFER,
				"₹" + amount + " transferred successfully to account "
						+ receiverAccount.getAccountNumber()
				);
		notificationService.createNotification(
				receiverAccount.getUser(),
				"Money Received",
				MessageType.TRANSFER,
				"₹" + amount + " received from account "
						+ senderAccount.getAccountNumber()
				);
		
		beneficiaryService.saveBeneficiary(senderAccount.getUser(), receiverAccount);
		
		return transactionMapper.entityToDto(tx);
		
	}

	@Override
	public List<TranseferResponseDto> getAllTransaction() {
		
		List<TranseferResponseDto> list = transactionRepo.findAll().stream().map(transactionMapper::entityToDto).toList();
		return list;
	}

	@Override
	public List<TranseferResponseDto> getTransactionByEmail(String email) {

          User user = userRepo.findByEmail(email).orElseThrow(() -> 
          new ResourceNotFoundException("User not found on this email "+email));
		
         List<TranseferResponseDto> list = transactionRepo.findById(user.getId()).
        		 stream().map(transactionMapper::entityToDto).toList();
		
		return list;
	}

	@Override
	public void delete(Integer id) {
		
		Transaction transaction = transactionRepo.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("No transaction on this id "+id));
		
		transactionRepo.delete(transaction);
		
	}

	private String generateTransactionReference() {

	    String reference;

	    do {
	        reference = "TXN" + System.currentTimeMillis();

	    } while (transactionRepo.existsByTransactionReference(reference));

	    return reference;
	}

}
