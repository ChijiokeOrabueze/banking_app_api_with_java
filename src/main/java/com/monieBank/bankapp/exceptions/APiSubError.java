package com.monieBank.bankapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

abstract class APiSubError {
}

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
class ApiValidationError extends APiSubError {

    private  String object;
    private String field;
    private Object rejectedValue;
    private String message;


    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
