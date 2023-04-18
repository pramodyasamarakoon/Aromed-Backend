package com.aromed.aromed.controller;

import com.aromed.aromed.Repository.CustomerRepository;
import com.aromed.aromed.Repository.LabCustomerRepository;
import com.aromed.aromed.model.Customer;
import com.aromed.aromed.model.LabCustomer;
import com.aromed.aromed.model.LabReports;
import com.aromed.aromed.service.CustomerService;
import com.aromed.aromed.service.LabCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/labCustomer")
@CrossOrigin("http://localhost:3000")
public class LabCustomerController {
    @Autowired
    private LabCustomerService service;
    private LabCustomerRepository repository;

    @PostMapping("/addCustomer")
    public LabCustomer addLabCustomer(@RequestBody LabCustomer labCustomer) {
        return service.addLabCustomer(labCustomer);
    }

    @PutMapping("/{customerId}/labReports")
    public LabCustomer addLabReportToCustomer(@PathVariable String customerId, @RequestBody LabReports labReports) {
        return service.addLabReportToCustomer(customerId, labReports);
    }


}
