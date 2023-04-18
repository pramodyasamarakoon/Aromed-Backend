package com.aromed.aromed.service;

import com.aromed.aromed.Repository.CustomerRepository;
import com.aromed.aromed.Repository.LabCustomerRepository;
import com.aromed.aromed.model.Customer;
import com.aromed.aromed.model.LabCustomer;
import com.aromed.aromed.model.LabReports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LabCustomerService {

    @Autowired
    private LabCustomerRepository repository;

    public LabCustomer addLabCustomer(LabCustomer labCustomer) {
        labCustomer.setCustomerId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(labCustomer);
    }

    public LabCustomer addLabReportToCustomer(String customerId, LabReports labReports) {
        LabCustomer labCustomer = repository.findById(customerId).orElse(null);
        if (labCustomer == null) {
            throw new RuntimeException("Lab customer not found with customerId: " + customerId);
        }
        if (labCustomer.getLabReports() == null) {
            labCustomer.setLabReports(new ArrayList<>());
        }
        labCustomer.getLabReports().add(labReports);
        return repository.save(labCustomer);
    }





}
