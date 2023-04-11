package com.aromed.aromed.controller;

import com.aromed.aromed.Repository.ChargesRepository;
import com.aromed.aromed.model.Charges;
import com.aromed.aromed.service.ChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charges")
@CrossOrigin("http://localhost:3000")

public class ChargesController {
    @Autowired
    private ChargesRepository repository;
    private ChargesService service;

    @PostMapping("/addCharge")
    public ResponseEntity<Charges> addCharge(
            @RequestParam(value = "chargeType") String chargeType,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "fee") int fee
    ) {
        try {
            Charges charge = new Charges();
            charge.setChargeType(chargeType);
            charge.setName(name);
            charge.setFee(fee);

            Charges savedCharge = repository.save(charge);

            return new ResponseEntity<>(savedCharge, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get all charges
    @GetMapping("/getAll")
    public List<Charges> getAllCharges() {
        return repository.findAll();
    }

    //    Get charge by charge id
    @GetMapping("/getChargeById")
    public ResponseEntity<Charges> getChargeByChargeId(@RequestParam(value ="chargeId" ) String chargeId) {
        Charges charges = repository.findChargesByChargeId(chargeId);
        if (charges == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(charges, HttpStatus.OK);
    }

//    Update charge by chargeId
    @PutMapping("/updateChargeById")
    public ResponseEntity<?> updateChargeById(@RequestParam String chargeId, @RequestParam String chargeType, @RequestParam String name, @RequestParam int fee) {
        try {
            Charges charge = repository.findChargesByChargeId(chargeId);
            if (charge == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            charge.setChargeType(chargeType);
            charge.setName(name);
            charge.setFee(fee);
            repository.save(charge);
            return new ResponseEntity<>(charge, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating charge: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//     delete charge
    @DeleteMapping("/deleteChargeByChargeId")
    public ResponseEntity<?> deleteChargeByChargeId(@RequestParam String chargeId) {
        try {
            repository.deleteChargesByChargeId(chargeId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



}
