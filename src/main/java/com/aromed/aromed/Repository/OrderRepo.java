package com.aromed.aromed.Repository;

import com.aromed.aromed.model.OrderMedicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends MongoRepository<OrderMedicine, String> {
}
