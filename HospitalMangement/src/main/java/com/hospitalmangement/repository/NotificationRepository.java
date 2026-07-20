package com.hospitalmangement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitalmangement.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Integer> {

    List<Notification> findByReadStatusFalseOrderByCreatedAtDesc();
}