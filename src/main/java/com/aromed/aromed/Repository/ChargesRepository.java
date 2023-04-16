package com.aromed.aromed.Repository;

import com.aromed.aromed.model.Charges;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChargesRepository extends MongoRepository<Charges, String > {
    Charges findChargesByChargeId(String chargeId);

    Charges findByChargeId(String chargeId);

    Charges deleteChargesByChargeId(String chargeId);

    List<Charges> findChargesByChargeType(String chargeType);


}
