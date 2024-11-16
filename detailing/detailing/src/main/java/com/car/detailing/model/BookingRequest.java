package com.car.detailing.model;

public class BookingRequest {
    private String serviceType;
    private String appointmentDate;

    // Getters and setters
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
