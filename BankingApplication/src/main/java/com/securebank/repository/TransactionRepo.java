package com.securebank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securebank.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

	 boolean existsByTransactionReference(String transactionReference);
}
