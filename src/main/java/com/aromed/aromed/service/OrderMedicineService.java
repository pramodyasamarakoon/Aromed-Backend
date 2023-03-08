package com.aromed.aromed.service;

import com.aromed.aromed.Dto.OrderMedicineDto;
import com.aromed.aromed.Repository.OrderMedicineRepository;
import com.aromed.aromed.Repository.OrderRepo;
import com.aromed.aromed.model.OrderMedicine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional
public class OrderMedicineService {

    @Autowired
    public OrderMedicineRepository orderMedicineRepository ;

    public OrderMedicine addOrder ( OrderMedicineDto orderMedicine ){
        try {
            OrderMedicine orderMedicine1 = new OrderMedicine();
            orderMedicine1.setOrderMedicineId(UUID.randomUUID().toString().split("-")[0]);
            orderMedicine1.setName(orderMedicine.getName());
            orderMedicine1.setMNumber(orderMedicine.getMNumber());
            orderMedicine1.setEmail(orderMedicine.getEmail());
            orderMedicine1.setNic(orderMedicine.getNic());
            orderMedicine1.setAddress(orderMedicine.getAddress());
            orderMedicine1.setPrescription(orderMedicine.getPrescription());
            orderMedicine1.setMessage(orderMedicine.getMessage());
            orderMedicine1.setRequestStatus(orderMedicine.isRequestStatus());
            orderMedicine1.setPlacedStatus(orderMedicine.isPlacedStatus());
            System.out.println(orderMedicine1);

            orderMedicineRepository.save(orderMedicine1);
            return orderMedicine1;
        }catch (Exception e){
            System.out.println("Exception in OrderMedicine Service");
            e.printStackTrace();
        }

        return null;
    }

    public OrderMedicine getOrderMedicineByOrderMedicineId(String orderMedicineId){
        try {
            return orderMedicineRepository.findById(orderMedicineId).get();
        }catch (Exception e){
            System.out.println("Exception in Get OrderMedicine Service");
            e.printStackTrace();
            return null;
        }
    }
}
