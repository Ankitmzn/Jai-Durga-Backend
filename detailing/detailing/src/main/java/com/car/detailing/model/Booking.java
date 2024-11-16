package com.car.detailing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
    private Long id;

    private String customerName;
    private String carModel;
    private String serviceType;
    private LocalDateTime bookingDate;
    private String contactNumber;
    private String address;

    // Constructors
    public Booking() {
    }

    public Booking(String customerName, String carModel, String serviceType, LocalDateTime bookingDate, String contactNumber, String address) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.serviceType = serviceType;
        this.bookingDate = bookingDate;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public void setAppointmentDate(String appointmentDate) {
		// TODO Auto-generated method stub
		
	}

	public Object getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
}
