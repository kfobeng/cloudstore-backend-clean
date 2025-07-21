package com.cloudstore.service;

import com.cloudstore.dto.MobileMoneyRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Service
public class FlutterwaveService {

    @Value("${flutterwave.secret.key}")
    private String secretKey;

    public String initiateMobileMoney(MobileMoneyRequest request) throws IOException {
        URL url = new URL("https://api.flutterwave.com/v3/charges?type=mobile_money_ghana");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + secretKey);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInput = new Gson().toJson(request);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonInput.getBytes());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        return reader.lines().collect(Collectors.joining());
    }
}
