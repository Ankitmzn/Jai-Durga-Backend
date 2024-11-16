package com.car.detailing.service;

import com.car.detailing.model.Notification;
import com.car.detailing.model.User;
import com.car.detailing.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void notifyAdmin(User user, String message) {
        // Logic to notify the admin about a user action
        System.out.println("Admin notified about user: " + user.getUsername() + " with message: " + message);
    }

    public void sendNotification(User user, String message) {
        // Logic to send a notification to the user
        System.out.println("Notification sent to user: " + user.getUsername() + " with message: " + message);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
