package com.aromed.aromed.model;

import com.aromed.aromed.Dto.MedicineListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document (collection = "order_Medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMedicine {
    @Id
    private String orderMedicineId;
    private String name;
    private String mNumber;
    private String email;
    private String nic;
    private String address;
    private String prescription;
    private String message;
    private  boolean requestStatus;
    private boolean placedStatus;
    private boolean confirmedStatus = false;
    private List<MedicineListDto> medicineList;

    private int total = 0;
//    private int deliveryCharge = 500;

    public OrderMedicine(String name, String mNumber, String email, String nic, String address, String prescription, String message, boolean requestStatus, boolean placedStatus,boolean confirmedStatus, int total, int deliveryCharge ) {
        this.name = name;
        this.mNumber = mNumber;
        this.email = email;
        this.nic = nic;
        this.address = address;
        this.prescription = prescription;
        this.message = message;
        this.requestStatus = requestStatus;
        this.placedStatus = placedStatus;
        this.confirmedStatus = confirmedStatus;
        this.medicineList = new ArrayList<>();
        this.total = total;
//        this.deliveryCharge = deliveryCharge;
    }
}
