package com.aromed.aromed.controller;

import com.aromed.aromed.model.Charges;
import com.aromed.aromed.service.ChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/charges")
@CrossOrigin("http://localhost:3000")

public class ChargesController {
    @Autowired
    private ChargesService service;

    @PostMapping(value = "/addCharge", produces = APPLICATION_JSON_VALUE , consumes = APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    public Charges createCharge(@RequestBody Charges charges){
        return service.addCharge(charges);
    }

//    @GetMapping("/{charge}")
//    public Charges getCharge(@PathVariable String chargeId){
//        return service.getChargeByChargeId(chargeId);
//    }
}
