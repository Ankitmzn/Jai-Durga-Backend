package com.car.detailing.controller;

import com.car.detailing.model.User;
import com.car.detailing.service.NotificationService;
import com.car.detailing.service.UserService;
//import com.car.detailing.service.NotificationService; // Add notification service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;

//import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService; // Inject the UserService

    @Autowired
    private NotificationService notificationService; // Inject the NotificationService

    // @GetMapping("/register") // Endpoint to show the registration form
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Create a new User object for the form
        return "registration"; // Return the view name for the registration page
    }

    // Method for registering user
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register"; // Return to registration page if validation fails
        }

        // Set default role to 'USER' and admin can manually be assigned via database or during registration
        user.setMobile("USER"); // Default to USER role

        try {
            // Register the user
            userService.registerUser(user);

            // Notify admin about new user registration
            notificationService.notifyAdmin(user, null); // Notify admin about the registration

            model.addAttribute("message", "Registration successful!"); // Success message
            return "redirect:/login"; // Redirect to login page
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Username already taken. Please choose a different one."); // Error message
            return "register"; // Return to registration page
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred. Please try again."); // Generic error message
            return "register"; // Return to registration page
        }
    }
}
