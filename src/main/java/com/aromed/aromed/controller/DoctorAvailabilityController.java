package com.aromed.aromed.controller;

import com.aromed.aromed.Repository.DoctorAvailabilityRepository;
import com.aromed.aromed.model.Availability;
import com.aromed.aromed.model.DoctorAvailability;
import com.aromed.aromed.service.DoctorAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctorAvailability")
@CrossOrigin("http://localhost:3000")
public class DoctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityService service;
    private DoctorAvailabilityRepository repository;


//    Add Availability using DoctorId
    @PostMapping("/{doctorId}/createAvailabilities")
    public void createDoctorAvailabilities(@PathVariable String doctorId) {
        // Create doctor availabilities for each day from today to two months ahead
        service.createDoctorAvailabilities(doctorId);
    }


//    Update the availability
    @PutMapping("/updateAvailability")
    public ResponseEntity<String> updateAvailability(@RequestParam(value = "availabilityId") String availabilityId,
                                                     @RequestParam(value = "availability") String availability) {
        try {
            service.updateDoctorAvailability(availabilityId, availability);
            return ResponseEntity.ok("Availability updated successfully");
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Availability not found");
        }
    }

//    Get three weeks from today using doctorId
    @GetMapping("/getAvailability")
    public List<DoctorAvailability> getDoctorAvailabilities(
            @RequestParam(value = "doctorId") String doctorId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate
    ) {
        try {
            List<DoctorAvailability> availabilities = service.getDoctorAvailabilities(doctorId);
                LocalDate start = LocalDate.now();
                LocalDate end = start.plusWeeks(3);

                availabilities = availabilities.stream()
                        .filter(avail -> {
                            LocalDate fullDay = LocalDate.parse(avail.getFullDate());
                            return fullDay.compareTo(start) >= 0 && fullDay.compareTo(end) <= 0;
                        })
                        .collect(Collectors.toList());
            return availabilities;
        } catch (Exception e) {
            // handle the exception and return an appropriate response to the client
            return new ArrayList<>();
        }
    }

    @GetMapping("/getAvailabilityForCustomers")
    public List<DoctorAvailability> getDoctorAvailabilitiesForCustomers(
            @RequestParam(value = "doctorId") String doctorId,
            @RequestParam(value = "availability") String availability
    ) {
        try {
            List<DoctorAvailability> availabilities = service.getDoctorAvailabilities(doctorId);
            LocalDate start = LocalDate.now();
            LocalDate end = start.plusDays(6);

            availabilities = availabilities.stream()
                    .filter(avail -> {
                        LocalDate fullDay = LocalDate.parse(avail.getFullDate());
                        if (availability != null && !avail.getAvailability().equalsIgnoreCase(availability)) {
                            return false;
                        }
                        return fullDay.compareTo(start) >= 0 && fullDay.compareTo(end) <= 0;
                    })
                    .collect(Collectors.toList());
            return availabilities;
        } catch (Exception e) {
            // handle the exception and return an appropriate response to the client
            return new ArrayList<>();
        }
    }




}






