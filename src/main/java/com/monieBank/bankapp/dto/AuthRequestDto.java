package com.monieBank.bankapp.dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AuthRequestDto {

    @NotEmpty(message = "username is required.")
    private String userName;

    @NotEmpty(message = "password is required.")
    private String password;
}
