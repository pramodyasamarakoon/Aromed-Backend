package com.aromed.aromed.Repository;

import com.aromed.aromed.model.DoctorAvailability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAvailabilityRepository extends MongoRepository<DoctorAvailability, String> {

    List<DoctorAvailability> findByDoctorId(String doctorId);


}




