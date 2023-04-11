package com.aromed.aromed.service;

import com.aromed.aromed.Repository.ChargesRepository;
import com.aromed.aromed.model.Charges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChargesService {

    @Autowired
    private ChargesRepository repository;

    // Add Charge
    public Charges addCharge(Charges charges){
        charges.setChargeId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(charges);
    }

//     get All charges
    public List<Charges> getAllCharges() {
        return repository.findAll();
    }

//    Update charges by chargeId
    public Charges updateCharge(Charges charge) {
        return repository.save(charge);
    }

//    get charge by chargeId
    public Charges getChargeByChargeId(String chargeId) {
        return repository.findById(chargeId)
                .orElse(null);
    }

//    delete charge
    public void deleteChargeByChargeId(String chargeId) {
        Charges charge = repository.findByChargeId(chargeId);
        if (charge != null) {
            repository.delete(charge);
        } else {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
