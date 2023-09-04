package com.monieBank.bankapp.repository;

import com.monieBank.bankapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("update Account u set u.balance = ?2 where u.id = ?1")
    void updateBalanceById(Long accountId, BigInteger newBalance);
}
