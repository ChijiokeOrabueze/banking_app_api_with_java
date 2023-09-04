package com.monieBank.bankapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;


@AllArgsConstructor
@Data
public class AirtimeRequest {

    @NotNull(message = "source account is required.")
    private Long sourceAccount;

    @NotNull(message = "phone number is required.")
    private BigInteger phoneNumber;

    @NotNull(message = "network provider is required.")
    private String networkProvider;

    @NotNull(message = "amount is required.")
    private BigDecimal amount;
}
