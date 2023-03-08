package com.aromed.aromed.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.aromed.aromed.Dto.AppointmentDto;
import com.aromed.aromed.Repository.AppointmentRepository;
import com.aromed.aromed.Repository.CustomerRepository;
import com.aromed.aromed.model.Appointment;
import com.aromed.aromed.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private CustomerRepository customerRepository;

    public Appointment addAppointment (AppointmentDto appointmentDto){
        Appointment newAppointment = null;
//        appointment.setAppointmentId(UUID.randomUUID().toString().split("-")[0]);
        try {
            Customer existingCustoer= customerRepository.findByAppointmentId(appointmentDto.getAppointmentId());
            if(existingCustoer.getAppointmentId().equals(appointmentDto.getAppointmentId())){
                newAppointment = new Appointment(appointmentDto.getAppointmentId(), existingCustoer, appointmentDto.isPaymentStatus(), appointmentDto.getAmount());
                repository.save(newAppointment);
            }

        }catch (Exception e){
            System.out.println("Exception in Add appointment Service");
        }
        return newAppointment;
    }

    public Appointment getAppointmentByAppointmentId(String appointmentId){
        return repository.findById(appointmentId).get();
    }


}
