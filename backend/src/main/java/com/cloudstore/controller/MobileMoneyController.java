package com.cloudstore.controller;

import com.cloudstore.dto.MobileMoneyRequest;
import com.cloudstore.service.FlutterwaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/mobilemoney")
public class MobileMoneyController {

    private final FlutterwaveService flutterwaveService;

    // Constructor injection ensures the service is properly wired
    public MobileMoneyController(FlutterwaveService flutterwaveService) {
        this.flutterwaveService = flutterwaveService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePayment(@RequestBody MobileMoneyRequest request) {
        try {
            String response = flutterwaveService.initiateMobileMoney(request);
            return ResponseEntity.ok(response); // Returns Flutterwave’s JSON response
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Mobile money payment failed");
        }
    }
}
