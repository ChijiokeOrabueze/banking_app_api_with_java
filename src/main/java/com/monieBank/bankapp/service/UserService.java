package com.monieBank.bankapp.service;

import com.monieBank.bankapp.dto.UserRequestDto;
import com.monieBank.bankapp.model.Customer;

public interface UserService {

    Customer getUser (Long userId);

    Customer createUser(UserRequestDto user);
}
