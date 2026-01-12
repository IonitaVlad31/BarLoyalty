package com.barLoyalty.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@Service
public class QrCodeService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${QR_SERVICE_URL:http://qr-service:8082}")
    private String qrServiceUrl;

    public String generateQr(String data) {
        String url = qrServiceUrl + "/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of("data", data);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "Eroare la generarea QR: " + e.getMessage();
        }
    }
}
