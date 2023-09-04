package com.monieBank.bankapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;
    private BigDecimal discountedAmount = BigDecimal.ZERO;
    private Double rate = 0.0;

    @NotNull
    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime transactionDate;
    private TransactionType transactionType;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account destinationAccount;

}

