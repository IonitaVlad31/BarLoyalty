package com.barLoyalty.gateway.controller;

import com.barLoyalty.gateway.dto.TransactionRequest;
import com.barLoyalty.gateway.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transact")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> makeTransaction(@RequestBody TransactionRequest request) {
        try {
            String result = transactionService.processTransaction(request);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
