package com.fcmb.bankapp.controller;


import com.fcmb.bankapp.dto.ApiResponse;
import com.fcmb.bankapp.dto.UserRequestDto;
import com.fcmb.bankapp.model.Customer;
import com.fcmb.bankapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;


    @PostMapping()
    public ResponseEntity<ApiResponse<Customer>> createUser (@Valid @RequestBody UserRequestDto userRequest){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("user created successfully", 201, userService.createUser(userRequest)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Customer>> getUser (@PathVariable Long userId) {


        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("user fetched", 200, userService.getUser(userId)));
    }
}
