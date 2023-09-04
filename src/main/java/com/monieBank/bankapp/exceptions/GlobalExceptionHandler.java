package com.monieBank.bankapp.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,  WebRequest request){

        String error = "Malformed JSON request";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));

    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            WebRequest request
    ){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "invalid body",exception);


        List<APiSubError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError -> mapToSubError(FieldError))
                .collect(Collectors.toList());

        apiError.setSubErrors(errors);

        return buildResponseEntity(apiError);

    }

    private APiSubError mapToSubError(FieldError fieldError) {
        return ApiValidationError.builder()
                .field(fieldError.getField())
                .object(fieldError.getObjectName())
                .rejectedValue(fieldError.getRejectedValue())
                .message(fieldError.getDefaultMessage())
                .build();

    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleInvalidPathVariable (
            ConstraintViolationException exception,
            WebRequest request
    ){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "invalid path parameter", exception);

        List<APiSubError> errors = exception.getConstraintViolations()
                .stream()
                .map(violation -> mapConstraintToSubError(violation))
                .collect(Collectors.toList());

        apiError.setSubErrors(errors);

        return buildResponseEntity(apiError);
    }

    private APiSubError mapConstraintToSubError(ConstraintViolation<?> violation) {

        String[] v = violation.getPropertyPath().toString().split(".");
        for (String s: v){
            System.out.println(s);
        }
        System.out.println(violation.getPropertyPath().toString());
        return ApiValidationError.builder()
                .message(violation.getMessage())
                .rejectedValue(violation.getInvalidValue())
                .field(violation.getPropertyPath().toString())
                .object(violation.getPropertyPath().toString())
                .build();
    }


//    @ExceptionHandler(EntityNotFoundException.class)
//    protected ResponseEntity<Object> handleEntityNotFound(
//            EntityNotFoundException ex) {
//        ApiError apiError = new ApiError(NOT_FOUND);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
//    }


}
