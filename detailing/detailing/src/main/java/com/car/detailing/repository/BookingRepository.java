package com.car.detailing.repository;

import com.car.detailing.model.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	//List<Booking> findByCustomerNameContainingIgnoreCaseOrId(String query, String query2);
	//List<Booking> findByCustomerNameContainingIgnoreCaseOrId(String customerName, String id);
	List<Booking> findByCustomerNameContainingIgnoreCase(String customerName);

	List<Booking> findByCustomerNameContainingIgnoreCaseOrId(String customerName, Long id);


}
