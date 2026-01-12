package com.barLoyalty.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class QrCodeService {

    private final RestTemplate restTemplate;

    /**
     * In Docker, qr-service ruleaza pe http://qr-service:8081
     * Local, ruleaza pe http://localhost:8081
     */
    @Value("${QR_SERVICE_URL:http://qr-service:8081}")
    private String qrServiceUrl;

    public QrCodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateQr(String data) {
        String url = qrServiceUrl + "/generate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = Map.of("data", data);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }
}
