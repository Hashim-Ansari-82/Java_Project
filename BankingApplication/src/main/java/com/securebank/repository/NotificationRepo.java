package com.securebank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securebank.entity.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer>{

	List<Notification> findByUser_IdOrderByCreatedAtDesc(String email);
}
