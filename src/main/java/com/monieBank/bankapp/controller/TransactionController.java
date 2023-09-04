package com.monieBank.bankapp.controller;


import com.fcmb.bankapp.dto.*;
import com.monieBank.bankapp.dto.*;
import com.monieBank.bankapp.model.Transaction;
import com.monieBank.bankapp.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<TransferResponse>> doTransfer(@Valid @RequestBody TransferRequest transferRequest) {

        TransferResponse transfer = transactionService.doTransfer(transferRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("transfer successful.", 200, transfer));


    }

    @PostMapping("/airtime")
    public ResponseEntity<ApiResponse<AirtimeResponse>> buyAirtime(@Valid @RequestBody AirtimeRequest airtimeRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("airtime purchase successful", 200, transactionService.buyAirtime(airtimeRequest)));
    }

    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponse<List<Transaction>>> getTransactionHistory(@PathVariable String accountId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("fetched transaction history", 200, transactionService.getTransactionHistory(Long.valueOf(accountId))));

    }


}
