package com.aromed.aromed.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.aromed.aromed.Dto.AppointmentDto;
import com.aromed.aromed.Repository.AppointmentRepository;
import com.aromed.aromed.Repository.CustomerRepository;
import com.aromed.aromed.model.Appointment;
import com.aromed.aromed.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<LocalDate, Double> getTotalAmountPerDay() {
        List<Appointment> appointments = repository.findAll();
        Map<LocalDate, Double> totalAmountPerDay = new HashMap<>();

        for (Appointment appointment : appointments) {
            LocalDate appointmentDate = LocalDate.parse(appointment.getCustomer().getDate());
            double amount = appointment.getAmount();
            totalAmountPerDay.put(appointmentDate, totalAmountPerDay.getOrDefault(appointmentDate, 0.0) + amount);
        }

        return totalAmountPerDay;
    }

    public Map<String, Object> getTotalAmountAndCountPerDay() {
        List<Appointment> appointments = repository.findAll();
        Map<String, Object> result = new HashMap<>();
        double totalAmountThisYear = 0.0;
        double totalAmountThisMonth = 0.0;
        double totalAmountThisWeek = 0.0;
        int countThisYear = 0;
        int countThisMonth = 0;
        int countThisWeek = 0;

        for (Appointment appointment : appointments) {
            LocalDate appointmentDate = LocalDate.parse(appointment.getCustomer().getDate());
            double amount = appointment.getAmount();
            result.put(appointmentDate.toString(), ((Double) result.getOrDefault(appointmentDate.toString(), 0.0)) + amount);


            if (appointmentDate.getYear() == LocalDate.now().getYear()) {
                totalAmountThisYear += amount;
                countThisYear++;
            }

            if (appointmentDate.getYear() == LocalDate.now().getYear() &&
                    appointmentDate.getMonth() == LocalDate.now().getMonth()) {
                totalAmountThisMonth += amount;
                countThisMonth++;
            }

            if (appointmentDate.isAfter(LocalDate.now().minusDays(7))) {
                totalAmountThisWeek += amount;
                countThisWeek++;
            }
        }

        result.put("TotalAmountThisYear", totalAmountThisYear);
        result.put("TotalAmountThisMonth", totalAmountThisMonth);
        result.put("TotalAmountThisWeek", totalAmountThisWeek);
        result.put("AppointmentCountThisYear", countThisYear);
        result.put("AppointmentCountThisMonth", countThisMonth);
        result.put("AppointmentCountThisWeek", countThisWeek);

        return result;
    }




}
