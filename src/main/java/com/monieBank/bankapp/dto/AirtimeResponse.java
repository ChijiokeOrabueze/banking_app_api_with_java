package com.monieBank.bankapp.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;


@AllArgsConstructor
@Data
@Builder
public class AirtimeResponse {
    private Long sourceAccount;

    private BigInteger phoneNumber;

    private String networkProvider;

    private BigDecimal amount;
}
