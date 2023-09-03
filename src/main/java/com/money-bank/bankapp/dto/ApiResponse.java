package com.fcmb.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse<ResponseDataType> {

    private String message;
    private Integer statusCode;
    private ResponseDataType data;
}
