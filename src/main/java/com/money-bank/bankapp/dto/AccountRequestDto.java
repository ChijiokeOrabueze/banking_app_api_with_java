package com.fcmb.bankapp.dto;


import com.fcmb.bankapp.model.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
