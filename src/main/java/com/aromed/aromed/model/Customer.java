package com.aromed.aromed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document ( collection = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String customerId;
    private String appointmentId;
    private int appointmentNumber;
    private String name;
    private String mNumber;
    private String email;
    private String address;
    private String gender;
    private int age;
    private String nic;
    private String doctor;
    private String date;
    private String message;
    private boolean videoConference;
    private String pName;
}
