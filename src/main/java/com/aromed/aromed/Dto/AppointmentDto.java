package com.aromed.aromed.Dto;

import com.aromed.aromed.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AppointmentDto {
    private String appointmentId;
    private boolean paymentStatus;
    private double amount;
    private int appointmentNumber;
}
