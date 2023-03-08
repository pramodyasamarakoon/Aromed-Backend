package com.aromed.aromed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "DoctorsAvailability")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorsAvailability {
    @Id
    private String availabilityId;
//    private String
}
