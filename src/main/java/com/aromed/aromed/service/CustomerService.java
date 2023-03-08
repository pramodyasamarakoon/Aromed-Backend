package com.aromed.aromed.service;

import com.aromed.aromed.Repository.CustomerRepository;
import com.aromed.aromed.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

//    Add customer
    public Customer addCustomer (Customer customer){
        customer.setCustomerId(UUID.randomUUID().toString().split("-")[0]);
        customer.setAppointmentId(UUID.randomUUID().toString().split("-")[0]);

        // Generate appointmentNumber based on date and doctor
        String date = customer.getDate();
        String doctor = customer.getDoctor();
        int appointmentNumber = repository.countByDateAndDoctor(date, doctor) + 1;
        customer.setAppointmentNumber(appointmentNumber);

        return repository.save(customer);
    }

//    Get Customer by ID
    public Customer getCustomerByCustomerId(String taskId){
        return repository.findById(taskId).get();
    }

    // Check Appointment Status
    public Customer getAppointmentStatus(String appointmentId){
        Customer customer = repository.findByAppointmentId((appointmentId).describeConstable().orElse(null));
        if (customer == null) {
            return null;
        } else  {
            return customer;
        }
    }

    public List<Customer> getCustomersByDoctorAndDate(String doctor, LocalDate date) {
        return repository.findByDoctorAndDate(doctor, date);
    }



}
