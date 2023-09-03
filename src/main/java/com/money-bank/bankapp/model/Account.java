package com.fcmb.bankapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigInteger accountNumber;

    private BigDecimal balance = BigDecimal.ZERO;

    @NotNull
    private AccountType accountType;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer user;

    @NotNull
    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime startDate;
}
