package com.monieBank.bankapp.controller;


import com.monieBank.bankapp.dto.ApiResponse;
import com.monieBank.bankapp.dto.AuthRequestDto;
import com.monieBank.bankapp.dto.AuthResponseDto;
import com.monieBank.bankapp.dto.UserRequestDto;
import com.monieBank.bankapp.model.Customer;
import com.monieBank.bankapp.service.JwtService;
import com.monieBank.bankapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;


    @PostMapping()
    public ResponseEntity<ApiResponse<Customer>> createUser (@Valid @RequestBody UserRequestDto userRequest){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("user created successfully", 201, userService.createUser(userRequest)));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<AuthResponseDto>> authenticate (@Valid @RequestBody AuthRequestDto authRequest) throws Exception {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );

        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);

        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());


        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("authentication successful",
                        200,
                        AuthResponseDto.builder()
                                .accessToken(jwtService.generateToken(userDetails))
                                .build()
                        ));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<Customer>> getUser (@PathVariable Long userId) {


        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("user fetched", 200, userService.getUser(userId)));
    }
}
