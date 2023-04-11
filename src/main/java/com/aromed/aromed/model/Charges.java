package com.aromed.aromed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document( collection = "charges")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Charges {
    @Id
    private String chargeId = UUID.randomUUID().toString().split("-")[0] ;
    private String chargeType;
    private String name;
    private int fee;
}
