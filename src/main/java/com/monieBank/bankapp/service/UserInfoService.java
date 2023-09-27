package com.monieBank.bankapp.service;


import com.monieBank.bankapp.dto.UserRequestDto;
import com.monieBank.bankapp.model.Customer;
import com.monieBank.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Primary
public class UserInfoService implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Customer> userDetail = userRepository.findByUserName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public Customer getUser(Long userId) {

        Optional<Customer> user = userRepository.findById(userId);

        if (user.isEmpty()) throw new RuntimeException("user not found");

        return user.get();
    }

    @Override
    public Customer createUser(UserRequestDto user) {

        Customer customer = Customer.builder()
                .userName(user.getUserName())
                .name(user.getName())
                .roles(user.getRoles())
                .password(encoder.encode(user.getPassword()))
                .startDate(LocalDateTime.now())
                .build();

        customer = userRepository.save(customer);
        return customer;
    }
}
