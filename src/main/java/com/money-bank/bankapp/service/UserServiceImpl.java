package com.fcmb.bankapp.service;

import com.fcmb.bankapp.dto.UserRequestDto;
import com.fcmb.bankapp.model.Customer;
import com.fcmb.bankapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
                .startDate(LocalDateTime.now())
                .build();

        customer = userRepository.save(customer);
        return customer;
    }
}
