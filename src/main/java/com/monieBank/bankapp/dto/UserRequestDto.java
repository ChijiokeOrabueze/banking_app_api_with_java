package com.monieBank.bankapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRequestDto {

    @NotEmpty(message = "username is required.")
    String userName;

    @NotEmpty(message = "name is required.")
    private String name;

    @NotEmpty(message = "password is required.")
    private String password;

    private String roles;
}
