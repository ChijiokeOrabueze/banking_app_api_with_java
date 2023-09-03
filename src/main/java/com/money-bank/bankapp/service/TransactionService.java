package com.fcmb.bankapp.service;

import com.fcmb.bankapp.dto.AirtimeRequest;
import com.fcmb.bankapp.dto.AirtimeResponse;
import com.fcmb.bankapp.dto.TransferRequest;
import com.fcmb.bankapp.dto.TransferResponse;
import com.fcmb.bankapp.model.Transaction;

import java.util.List;

public interface TransactionService {

    TransferResponse doTransfer(TransferRequest transferRequest);

    AirtimeResponse buyAirtime(AirtimeRequest airtimeRequest);

    List<Transaction> getTransactionHistory(Long accountId);
}
