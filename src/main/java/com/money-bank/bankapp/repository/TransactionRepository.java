package com.fcmb.bankapp.repository;

import com.fcmb.bankapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.transactionDate >= :transactionDate")
    List<Transaction> findAllWhereTransactionDateIsGte(
            @Param("transactionDate") LocalDateTime transactionDate);

    @Query("select t from Transaction t where t.sourceAccount.id = :accountId")
    List<Transaction> findAllWhereSourceAccountIdEquals(@Param("accountId") Long accountId);
}
