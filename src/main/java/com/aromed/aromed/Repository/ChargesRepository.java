package com.aromed.aromed.Repository;

import com.aromed.aromed.model.Charges;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChargesRepository extends MongoRepository<Charges, String > {

}
