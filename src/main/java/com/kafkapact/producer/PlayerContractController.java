package com.kafkapact.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerContractController {
    @Autowired
    private PlayerContractProducer playerContractProducer;

    @PostMapping("/sign")
    public ResponseEntity<Object> sendDetails(@RequestBody PlayerContract contract) {
        playerContractProducer.sendContractDetails(contract);

        return ResponseEntity.ok().build();
    }
}
