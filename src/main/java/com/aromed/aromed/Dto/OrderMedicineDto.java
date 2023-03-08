package com.aromed.aromed.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class OrderMedicineDto {
    private String name;
    private String mNumber;
    private String email;
    private String nic;
    private String address;
    private String prescription;
    private String message;
    private boolean requestStatus;
    private boolean placedStatus;
}
