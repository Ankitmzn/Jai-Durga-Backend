package com.car.detailing.controller;

import com.car.detailing.model.User;
import com.car.detailing.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Updated method to send notification
    @PostMapping("/sendNotification")
    public void sendNotification(@RequestParam String username, @RequestParam String message) {
        // Create or retrieve the User object (assuming you have a method to fetch User by username)
        User user = getUserByUsername(username);  // You will need to implement this method

        // Now send the notification
        notificationService.sendNotification(user, message);
    }

    // Sample method to retrieve a User by username (for example purposes)
    private User getUserByUsername(String username) {
        // Retrieve the user from the database or create a new User for demonstration purposes
        // You can implement a method in your service layer to fetch the user from the database
        // For now, returning a dummy user
        User user = new User();
        user.setUsername(username);
        user.setEmail(username + "@example.com");  // For example purposes
        return user;
    }
}
