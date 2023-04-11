package com.aromed.aromed.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Doctor_Availability")
public class DoctorAvailability {

    @Id
    private String id;

    private String doctorId;

    private String fullDate;

    private String year;

    private String month;

    private String date;

    private String availability;

    public DoctorAvailability() {
    }

    public DoctorAvailability(String doctorId, String fullDate, String year, String month, String date, String availability) {
        this.doctorId = doctorId;
        this.fullDate = fullDate;
        this.year = year;
        this.month = month;
        this.date = date;
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}


