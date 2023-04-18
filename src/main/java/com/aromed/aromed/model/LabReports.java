package com.aromed.aromed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "labReports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabReports {
    @Id
    private String reportId;
    private double price;
}
