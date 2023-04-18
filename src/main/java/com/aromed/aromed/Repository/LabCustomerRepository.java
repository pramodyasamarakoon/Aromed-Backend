package com.aromed.aromed.Repository;

import com.aromed.aromed.model.LabCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LabCustomerRepository extends MongoRepository<LabCustomer, String> {


}
