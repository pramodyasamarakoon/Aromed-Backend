package com.aromed.aromed.Repository;

import com.aromed.aromed.model.OrderMedicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderMedicineRepository extends MongoRepository<OrderMedicine, String> {
    public Optional<OrderMedicine> findOrderMedicineByOrderMedicineId(String orderMedicineId);
}
