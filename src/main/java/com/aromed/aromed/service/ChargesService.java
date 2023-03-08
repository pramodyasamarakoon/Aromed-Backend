package com.aromed.aromed.service;

import com.aromed.aromed.Repository.ChargesRepository;
import com.aromed.aromed.model.Charges;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Charges> getAllCharges(){
        return repository.findAll();
    }

}
