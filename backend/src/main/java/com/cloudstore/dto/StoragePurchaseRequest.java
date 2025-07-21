package com.cloudstore.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoragePurchaseRequest {
    private String userEmail;
    private Long amount;          // Amount in GHS
    private String phoneNumber;   // Mobile Money number
    private String network;       // MTN, Vodafone, AirtelTigo
    private String txRef;
}
