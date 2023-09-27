package com.monieBank.bankapp.dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthRequestDto {

    @NotEmpty(message = "username is required.")
    private String userName;

    @NotEmpty(message = "password is required.")
    private String password;
}
