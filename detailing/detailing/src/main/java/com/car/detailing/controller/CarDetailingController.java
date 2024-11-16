package com.car.detailing.controller;

import com.car.detailing.model.Booking;
import com.car.detailing.model.LoginRequest;
import com.car.detailing.model.User;
import com.car.detailing.service.BookingService;
import com.car.detailing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class CarDetailingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Username: " + loginRequest.getUsername());
        System.out.println("Password: " + loginRequest.getPassword());

        boolean isAuthenticated = userService.authenticate(loginRequest);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean isRegistered = userService.saveUser(user);
        if (isRegistered) {
            return ResponseEntity.ok("Registration successful");
        } else {
            return ResponseEntity.status(400).body("Registration failed. User already exists.");
        }
    }
    // Booking endpoint (only authenticated users can make bookings)
    @PostMapping("/booking")
    @PreAuthorize("isAuthenticated()") // This will ensure that the user is authenticated
    public ResponseEntity<String> bookService(@RequestBody Booking booking) {
        bookingService.saveBooking(booking); // Save the booking in the database
        return ResponseEntity.ok("Booking successful");
    }
    
}
