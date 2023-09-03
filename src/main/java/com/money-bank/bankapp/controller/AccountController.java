package com.fcmb.bankapp.controller;


import com.fcmb.bankapp.dto.AccountRequestDto;
import com.fcmb.bankapp.dto.ApiResponse;
import com.fcmb.bankapp.model.Account;
import com.fcmb.bankapp.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping()
    ResponseEntity<ApiResponse<Account>> createAccount (@Valid @RequestBody AccountRequestDto accountRequest){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("account created", 201, accountService.create(accountRequest)));
    }

}
