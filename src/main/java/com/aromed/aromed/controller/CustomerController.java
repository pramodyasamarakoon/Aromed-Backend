package com.aromed.aromed.controller;

import com.aromed.aromed.Repository.CustomerRepository;
import com.aromed.aromed.model.Appointment;
import com.aromed.aromed.model.Customer;
import com.aromed.aromed.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerService service;
    private CustomerRepository repository;

    @PostMapping(value = "/videoConference", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }

    @GetMapping("/{customer}")
    public Customer getCustomer(@PathVariable String customerId){
        return service.getCustomerByCustomerId(customerId);
    }

    @GetMapping("appointmentId/{AppointmentId}")
    public Customer getAppointment(@PathVariable String AppointmentId){
        return service.getAppointmentStatus(AppointmentId);
    }


//    @GetMapping("/appointments/{doctor}/{date}")
//    public List<Customer> getAppointmentsForUserAndDate(@PathVariable String doctor, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
//        return repository.findByD
//    }

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

//    @GetMapping("/loadAppointments")
//    public List<Customer> getCustomersByDoctorAndDate(@RequestParam String doctor, @RequestParam String date) {
//        try {
//            LocalDate localDate = LocalDate.parse(date);
//            return repository.findByDoctorAndDate(doctor, localDate);
//        } catch (DateTimeParseException e) {
//            // handle the parse exception
//            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format", e);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @GetMapping("/loadAppointments")
    public ResponseEntity<List<Customer>> getAppointmentsByDate(@RequestParam(value = "date") String dateStr, @RequestParam(value = "doctor") String doctorId) {

        try{
            List<Customer> appointments = repository.findByDate(dateStr);
        if (appointments != null) {
            List<Customer> matchingAppointments = new ArrayList<>();
            for (Customer appointment : appointments) {
                if (appointment.getDoctor().equals(doctorId)) {
                    matchingAppointments.add(appointment);
                }
            }
            return ResponseEntity.ok().body(matchingAppointments);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (DateTimeParseException e) {
        return ResponseEntity.badRequest().build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }









}
