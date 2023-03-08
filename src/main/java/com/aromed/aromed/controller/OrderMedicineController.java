package com.aromed.aromed.controller;

import com.aromed.aromed.Dto.OrderMedicineDto;
import com.aromed.aromed.model.Customer;
import com.aromed.aromed.model.OrderMedicine;
import com.aromed.aromed.service.OrderMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class OrderMedicineController {

    @Autowired
    private OrderMedicineService service;

    @PostMapping(value = "/createOrder", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE )
//    @ResponseStatus(HttpStatus.CREATED)
    public OrderMedicine createOrder(@RequestBody OrderMedicineDto orderMedicine){
        return service.addOrder(orderMedicine);
    }

    @GetMapping("/getOrderId/{orderid}")
    public OrderMedicine getOrderMedicine(@PathVariable String orderid){
        return service.getOrderMedicineByOrderMedicineId(orderid);
    }


}
