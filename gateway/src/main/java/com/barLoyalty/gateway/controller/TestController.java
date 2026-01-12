package com.barLoyalty.gateway.controller;

import com.barLoyalty.gateway.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping(value = "/qr", produces = "image/svg+xml")
    public String getQr(@RequestParam String text) {
        return qrCodeService.generateQr(text);
    }
}
