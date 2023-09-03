package com.fcmb.bankapp.service;

import com.fcmb.bankapp.dto.UserRequestDto;
import com.fcmb.bankapp.model.Customer;

public interface UserService {

    Customer getUser (Long userId);

    Customer createUser(UserRequestDto user);
}
