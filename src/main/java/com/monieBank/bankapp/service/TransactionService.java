package com.monieBank.bankapp.service;

import com.monieBank.bankapp.dto.AirtimeRequest;
import com.monieBank.bankapp.dto.AirtimeResponse;
import com.monieBank.bankapp.dto.TransferRequest;
import com.monieBank.bankapp.dto.TransferResponse;
import com.monieBank.bankapp.model.Transaction;

import java.util.List;

public interface TransactionService {

    TransferResponse doTransfer(TransferRequest transferRequest);

    AirtimeResponse buyAirtime(AirtimeRequest airtimeRequest);

    List<Transaction> getTransactionHistory(Long accountId);
}
