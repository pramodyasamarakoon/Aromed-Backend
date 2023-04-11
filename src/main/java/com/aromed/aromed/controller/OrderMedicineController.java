package com.aromed.aromed.controller;

import com.aromed.aromed.Dto.MedicineListDto;
import com.aromed.aromed.Dto.OrderMedicineDto;
import com.aromed.aromed.Repository.OrderMedicineRepository;
import com.aromed.aromed.model.Customer;
import com.aromed.aromed.model.OrderMedicine;
import com.aromed.aromed.service.OrderMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class OrderMedicineController {

    @Autowired
    private OrderMedicineService service;
    private OrderMedicineRepository repository;

    @PostMapping(value = "/createOrder", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE )
//    @ResponseStatus(HttpStatus.CREATED)
    public OrderMedicine createOrder(@RequestBody OrderMedicineDto orderMedicine){
        return service.addOrder(orderMedicine);
    }

//    @PostMapping(value = "/createOrder", produces = APPLICATION_JSON_VALUE , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public OrderMedicine createOrder(@RequestBody OrderMedicineDto orderMedicineDto, @RequestPart("prescription") MultipartFile prescriptionFile) {
//        try {
//            byte[] prescriptionBytes = prescriptionFile.getBytes();
//            String prescriptionBase64 = Base64.getEncoder().encodeToString(prescriptionBytes);
//            orderMedicineDto.setPrescription(prescriptionBase64);
//
//            OrderMedicine orderMedicine = service.addOrder(orderMedicineDto);
//
//            return orderMedicine;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping("/getOrders")
    public List<OrderMedicine> getOrders(@RequestParam boolean requestStatus, @RequestParam boolean placedStatus, @RequestParam boolean confirmedStatus){
        return service.getOrdersByRequestStatusAndPlacedStatus(requestStatus, placedStatus, confirmedStatus);
    }




    @GetMapping("/getOrderId")
    public OrderMedicine getOrderMedicine(@RequestParam String orderId){
        return service.getOrderMedicineByOrderMedicineId(orderId);
    }

//    Add medicine to the order
    @PostMapping("/addMedicine")
    public ResponseEntity<OrderMedicine> addMedicineToOrder(
            @RequestParam(value = "orderId") String orderId,
            @RequestBody MedicineListDto medicineListDto
    ) {
        try {
            OrderMedicine orderMedicine = service.getOrderMedicineByOrderMedicineId(orderId);
            if (orderMedicine == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<MedicineListDto> medicineList = orderMedicine.getMedicineList();
            if (medicineList == null) {
                medicineList = new ArrayList<>();
            }
            medicineList.add(medicineListDto);

            orderMedicine.setMedicineList(medicineList);

            service.updateOrderMedicine(orderMedicine);

            return new ResponseEntity<>(orderMedicine, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

// Update requestStatus and total when submitting the medicine by pharmacist
    @PutMapping("/updateMedicines")
    public ResponseEntity<OrderMedicine> updateOrderMedicine(
            @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "total") int total,
            @RequestParam(value = "requestStatus") boolean requestStatus) {
        try {
            OrderMedicine updatedOrderMedicine = service.updateWhenSubmitTheMedicineByPharmacist(orderId, total, requestStatus);
            return new ResponseEntity<>(updatedOrderMedicine, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update requestStatus and total when submitting the medicine by pharmacist
    @PutMapping("/confirmingOrders")
    public ResponseEntity<OrderMedicine> confirmingOrders(
            @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "confirmedStatus") boolean confirmedStatus) {
        try {
            OrderMedicine confirmOrderMedicine = service.confirmingOrdersByPharmacist(orderId, confirmedStatus);
            return new ResponseEntity<>(confirmOrderMedicine, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    Update placedStatus when placing the order by customer
    @PutMapping("/PlacingOrders")
    public ResponseEntity<OrderMedicine> placingOrders(
            @RequestParam(value = "orderId") String orderId,
            @RequestParam(value = "placedStatus") boolean placedStatus
    ){
        try {
            OrderMedicine placingOrderMedicine = service.placingOrderByCustomer( orderId, placedStatus );
            return new ResponseEntity<>(placingOrderMedicine, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

//    delete order
@DeleteMapping("/deleteOrder")
public ResponseEntity<String> deleteOrderMedicine(@RequestParam String orderId) {
    // Call the service method to delete the order by orderId
    service.deleteOrderMedicineById(orderId);
    return ResponseEntity.ok("Order with orderId " + orderId + " deleted successfully");
}






}
