package com.cloudstore.controller;

import com.cloudstore.dto.MobileMoneyRequest;
import com.cloudstore.dto.StoragePurchaseRequest;
import com.cloudstore.service.FlutterwaveService;
import com.cloudstore.service.StorageUpgradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/storage")
public class StoragePurchaseController {

    private final FlutterwaveService flutterwaveService;
    private final StorageUpgradeService storageUpgradeService;

    public StoragePurchaseController(
        FlutterwaveService flutterwaveService,
        StorageUpgradeService storageUpgradeService
    ) {
        this.flutterwaveService = flutterwaveService;
        this.storageUpgradeService = storageUpgradeService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseStorage(@RequestBody StoragePurchaseRequest request) {
        try {
            // 1. Create MobileMoneyRequest from incoming DTO
            MobileMoneyRequest momo = MobileMoneyRequest.builder()
                .phoneNumber(request.getPhoneNumber())
                .amount(String.valueOf(request.getAmount()))
                .currency("GHS")
                .network(request.getNetwork())
                .email(request.getUserEmail())
                .txRef(request.getTxRef())
                .build();

            // 2. Call Flutterwave API
            String flutterwaveResponse = flutterwaveService.initiateMobileMoney(momo);

            // 3. You'd normally parse flutterwaveResponse JSON here and confirm payment
            boolean paymentWasSuccessful = true; // placeholder

            if (paymentWasSuccessful) {
                Long bonusBytes = request.getAmount() * 50_000_000L; // 1 GHS = 50MB
                boolean upgraded = storageUpgradeService.upgradeStorage(request.getUserEmail(), bonusBytes);

                if (upgraded) {
                    return ResponseEntity.ok("✅ Storage upgraded by " + (bonusBytes / 1_000_000) + " MB.");
                } else {
                    return ResponseEntity.badRequest().body("❌ User not found.");
                }
            } else {
                return ResponseEntity.status(402).body("❌ Payment failed.");
            }

        } catch (IOException e) {
            return ResponseEntity.status(500).body("🚨 Error connecting to Flutterwave API.");
        }
    }
}
