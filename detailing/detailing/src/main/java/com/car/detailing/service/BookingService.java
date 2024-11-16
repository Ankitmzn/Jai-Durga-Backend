package com.car.detailing.service;

import com.car.detailing.model.Booking;
import com.car.detailing.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        if (booking != null) {
            System.out.println("Saving booking: " + booking);
            bookingRepository.save(booking);
        } else {
            System.out.println("Booking object is null!");
        }
        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> searchBookings(String query) {
        try {
            Long id = Long.parseLong(query);
            return bookingRepository.findByCustomerNameContainingIgnoreCaseOrId(query, id);
        } catch (NumberFormatException e) {
            return bookingRepository.findByCustomerNameContainingIgnoreCase(query);
        }
    }
}
