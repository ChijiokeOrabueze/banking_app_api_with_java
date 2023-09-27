package com.monieBank.bankapp.dto;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class AuthResponseDto {

    private String accessToken;
}
