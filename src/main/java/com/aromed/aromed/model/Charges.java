package com.aromed.aromed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "charges")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Charges {
    @Id
    private String chargeId;
    private String name;
    private double fee;
}
