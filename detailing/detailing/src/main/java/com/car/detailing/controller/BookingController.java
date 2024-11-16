package com.car.detailing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.car.detailing.model.Booking;
import com.car.detailing.model.User;
import com.car.detailing.service.BookingService;
import com.car.detailing.service.NotificationService;
import com.car.detailing.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        // Save the booking to the database
        bookingService.saveBooking(booking);

        // Notify admin (if a user exists, assuming user info is in booking)
        User user = userService.getUserById(booking.getUserId());
        if (user != null) {
            notificationService.notifyAdmin(user, "New booking by " + user.getUsername());
        }

        return ResponseEntity.ok("Booking created successfully");
    }

    // Endpoint to fetch all bookings
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // New endpoint to search bookings by customer name
    @GetMapping("/bookings/search")
    public List<Booking> searchBookings(@RequestParam("customerName") String customerName) {
        return bookingService.searchBookings(customerName);
    }
}
