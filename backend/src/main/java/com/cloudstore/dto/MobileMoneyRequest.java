package com.cloudstore.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileMoneyRequest {
    private String phoneNumber;
    private String amount;
    private String currency;
    private String network;
    private String email;
    private String txRef;
}


