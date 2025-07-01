package org.dtd6003.personalfinancetrackerbackend.transaction.controller;


import jakarta.validation.Valid;
import org.dtd6003.personalfinancetrackerbackend.transaction.dto.CreateTransactionRequest;
import org.dtd6003.personalfinancetrackerbackend.transaction.dto.TransactionResponse;
import org.dtd6003.personalfinancetrackerbackend.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService service;
    public TransactionController(TransactionService service){
        this.service = service;
    }
    //add a route that makes transactions
    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody CreateTransactionRequest req){
        TransactionResponse response = service.inputTransaction(req);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(){
        List<TransactionResponse> response = service.getAllTransactions();
        return ResponseEntity.status(201).body(response);
    }
}
