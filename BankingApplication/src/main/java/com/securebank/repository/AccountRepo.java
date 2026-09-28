package com.securebank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securebank.entity.Account;
import com.securebank.entity.User;
import com.securebank.enums.AccountStatus;
import com.securebank.enums.AccountType;


@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>{

	boolean existsByAccountNumber(String accountNumber);
	boolean existsByUser_IdAndAccountType(Integer userId, AccountType accountType);
	boolean existsByCustomerId(String customerId);
	List<Account> findByUser_Email(String email);
	List<Account> findByStatus(AccountStatus status);
	Optional<Account> findByUserAndAccountType(User user,AccountType accountType);
	
}
