package com.aromed.aromed.controller;

import com.aromed.aromed.Dto.AppointmentDto;
import com.aromed.aromed.Repository.AppointmentRepository;
import com.aromed.aromed.model.Appointment;
import com.aromed.aromed.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:3000")
public class AppointmentController {

    @Autowired
    private AppointmentService service;
    private AppointmentRepository repository;

    @PostMapping(value = "/meetAppointment", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment createAppointment(@RequestBody AppointmentDto appointment){
        return service.addAppointment(appointment);
    }

    @GetMapping("/{appointment}")
    public Appointment getAppointment(@PathVariable String AppointmentId){
        return service.getAppointmentByAppointmentId(AppointmentId);
    }


}
