package com.aromed.aromed.Repository;

import com.aromed.aromed.model.Appointment;
import com.aromed.aromed.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
        public Customer findByAppointmentId(String appoinntmentId);
        int countByDateAndDoctor(String date, String doctor);

        List<Customer> findByDoctorAndDate(String doctor, LocalDate date);

        List<Customer> findByDate(String date);

}
