package com.hospitalmangement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitalmangement.entity.Notification;
import com.hospitalmangement.repository.NotificationRepository;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @GetMapping
    public List<Notification> getUnreadNotifications() {
        return repository.findByReadStatusFalseOrderByCreatedAtDesc();
    }
}