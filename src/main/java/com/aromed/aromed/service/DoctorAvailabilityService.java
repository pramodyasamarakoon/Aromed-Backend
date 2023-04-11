package com.aromed.aromed.service;

import com.aromed.aromed.Repository.DoctorAvailabilityRepository;
import com.aromed.aromed.model.Availability;
import com.aromed.aromed.model.DoctorAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorAvailabilityService {

    @Autowired
    private DoctorAvailabilityRepository repository;

    // Add new doctor
    public void createDoctorAvailabilities(String doctorId) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Loop through each day from today to two months ahead
        for (LocalDate date = today; date.isBefore(today.plusMonths(3)); date = date.plusDays(1)) {
            // Create a DoctorAvailability object for the current date
            String year = String.valueOf(date.getYear());
            String month = String.format("%02d", date.getMonthValue());
            String day = String.format("%02d", date.getDayOfMonth());
            String availabilityId = doctorId + year + month + day;
            DoctorAvailability availability = new DoctorAvailability(doctorId, date.toString(), year, month, day, "On Leave");
            availability.setId(availabilityId);

            // Save the DoctorAvailability object to the database
            repository.save(availability);
        }
    }

//    update
    public void updateDoctorAvailability(String availabilityId, String availability) throws ChangeSetPersister.NotFoundException {
        // Check if the availability record exists in the database
        Optional<DoctorAvailability> optionalAvailability = repository.findById(availabilityId);
        if (optionalAvailability.isPresent()) {
            // Update the availability data and save the record to the database
            DoctorAvailability doctorAvailability = optionalAvailability.get();
            doctorAvailability.setAvailability(availability);
            repository.save(doctorAvailability);
        } else {
            // Throw a NotFoundException if the availability record does not exist
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public List<DoctorAvailability> getDoctorAvailabilities(String doctorId) {
        return repository.findByDoctorId(doctorId);
    }

}



