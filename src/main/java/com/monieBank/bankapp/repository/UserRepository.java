package com.monieBank.bankapp.repository;

import com.monieBank.bankapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {
}