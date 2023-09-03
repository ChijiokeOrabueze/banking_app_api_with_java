package com.fcmb.bankapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @NotNull(message = "source account is required.")
    private Long sourceAccount;

    @NotNull(message = "destination account is required.")
    private Long destinationAccount;

    @NotNull(message = "amount is required.")
    private BigDecimal amount;

}
