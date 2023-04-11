package com.aromed.aromed.service;

import com.aromed.aromed.Dto.MedicineListDto;
import com.aromed.aromed.Dto.OrderMedicineDto;
import com.aromed.aromed.Repository.OrderMedicineRepository;
import com.aromed.aromed.Repository.OrderRepo;
import com.aromed.aromed.model.OrderMedicine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public List<OrderMedicine> getOrdersByRequestStatusAndPlacedStatus(boolean requestStatus, boolean placedStatus, boolean confirmedStatus) {
        return orderMedicineRepository.findByRequestStatusAndPlacedStatusAndConfirmedStatus(requestStatus, placedStatus, confirmedStatus);
    }



    public void updateOrderMedicine(OrderMedicine orderMedicine) {
        orderMedicineRepository.save(orderMedicine);
    }

//    Update the requestStatus and total when pharmacist submit the medicine
    public OrderMedicine updateWhenSubmitTheMedicineByPharmacist(String orderId, int total, boolean requestStatus) {
        try {
            OrderMedicine orderMedicine = orderMedicineRepository.findById(orderId)
                        .orElseThrow(() -> new RuntimeException("OrderMedicine not found with id " + orderId));
            orderMedicine.setTotal(total);
            orderMedicine.setRequestStatus(requestStatus);

            return orderMedicineRepository.save(orderMedicine);
        } catch (Exception e) {
            return null;
        }
    }

    //    Update the confirmingStatus when pharmacist submit the medicine
    public OrderMedicine confirmingOrdersByPharmacist(String orderId, boolean confirmedStatus) {
        try {
            OrderMedicine orderMedicine = orderMedicineRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("OrderMedicine not found with id " + orderId));
            orderMedicine.setConfirmedStatus(confirmedStatus);

            return orderMedicineRepository.save(orderMedicine);
        } catch (Exception e) {
            return null;
        }
    }

//     Update the placedStatus when customer placed the order
    public OrderMedicine placingOrderByCustomer(String orderId, boolean placedStatus){
        try {
            OrderMedicine orderMedicine = orderMedicineRepository.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("OrderMedicine not found with id " + orderId));
            orderMedicine.setPlacedStatus(placedStatus);

            return orderMedicineRepository.save(orderMedicine);
        } catch ( Exception e){
            return null;
        }
    }

//    Delete order
public void deleteOrderMedicineById(String orderId) {
    orderMedicineRepository.deleteById(orderId);
}



}



