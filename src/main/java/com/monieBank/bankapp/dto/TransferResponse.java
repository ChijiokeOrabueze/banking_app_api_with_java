package com.monieBank.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferResponse {


    private Long sourceAccount;

    private Long destinationAccount;

    private BigDecimal amount;
}
