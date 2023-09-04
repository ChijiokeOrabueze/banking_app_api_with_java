package com.monieBank.bankapp.repository;

import com.monieBank.bankapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserName(String username);
}
