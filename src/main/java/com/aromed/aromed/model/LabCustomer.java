package com.aromed.aromed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document ( collection = "labCustomer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabCustomer {
    @Id
    private String customerId;
    private String name;
    private String mNumber;
    private String email;
    private String address;
    private String nic;
    private List<LabReports> labReports;
}
