package com.monieBank.bankapp.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuthResponseDto {

    private String accessToken;
}
