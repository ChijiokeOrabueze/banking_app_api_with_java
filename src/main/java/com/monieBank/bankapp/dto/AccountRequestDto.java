package com.monieBank.bankapp.dto;


import com.monieBank.bankapp.model.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {

    @NotNull(message = "user id is required.")
    private Long userId;

    @NotNull(message = "account type is required.")
    private AccountType accountType;
}
