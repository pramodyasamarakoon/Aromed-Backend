package com.aromed.aromed.model;

import java.util.Date;

public class Availability {
    private String availabilityId;
    private Date date;
    private String availability;
    private String doctorId;

    public Availability(String availabilityId, Date date, String availability, String doctorId) {
        this.availabilityId = availabilityId;
        this.date = date;
        this.availability = availability;
        this.doctorId = doctorId;
    }

    public String getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(String availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}

