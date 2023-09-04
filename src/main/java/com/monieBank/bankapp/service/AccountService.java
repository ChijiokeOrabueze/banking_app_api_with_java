package com.monieBank.bankapp.service;

import com.monieBank.bankapp.dto.AccountRequestDto;
import com.monieBank.bankapp.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account create (AccountRequestDto accountRequest);
    Account findAccountById (Long accountId);

    Account withDraw (Account account, BigDecimal amount, Boolean saveTransaction);

    Account deposit (Account account, BigDecimal amount, Boolean saveTransaction);

    List<Account> transfer (Account sourceAccount, Account destinationAccount, BigDecimal amount, BigDecimal discount);


}
