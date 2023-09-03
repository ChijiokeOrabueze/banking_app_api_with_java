package com.fcmb.bankapp.service;

import com.fcmb.bankapp.dto.AccountRequestDto;
import com.fcmb.bankapp.model.Account;
import com.fcmb.bankapp.model.Customer;
import com.fcmb.bankapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    public Account create(AccountRequestDto accountRequest) {

        Customer user = userService.getUser(accountRequest.getUserId());

        BigInteger accountNumber = generateAccountNumber();

        Account account = Account.builder()
                .accountType(accountRequest.getAccountType())
                .accountNumber(accountNumber)
                .user(user)
                .balance(BigDecimal.ZERO)
                .startDate(LocalDateTime.now())
                .build();

        account = accountRepository.save(account);

        return account;
    }

    private BigInteger generateAccountNumber() {

        return new BigInteger("0");
    }

    @Override
    public Account findAccountById(Long accountId) {

        System.out.println(accountId + " hello");
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isEmpty()){
            throw new RuntimeException("account not found.");
        }
        return account.get();
    }

    @Override
    public Account withDraw(Account account, BigDecimal amount, Boolean saveTransaction) {

        if (account.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));

        if (saveTransaction) accountRepository.save(account);
        return account;
    }

    @Override
    public Account deposit(Account account, BigDecimal amount, Boolean saveTransaction) {

        account.setBalance(account.getBalance().add(amount));

        if (saveTransaction){
            accountRepository.save(account);
        }
        return account;
    }

    @Override
    public List<Account> transfer(Account sourceAccount, Account destinationAccount, BigDecimal amount, BigDecimal discount) {

        BigDecimal amountToWithdraw = amount.compareTo(discount) < 0 ? new BigDecimal(0): amount.subtract(discount);

        withDraw(sourceAccount, amountToWithdraw, false);
        deposit(destinationAccount, amount, false);

        List<Account> accounts = Arrays.asList(sourceAccount, destinationAccount);

        accountRepository.saveAll(accounts);
        return accounts;
    }


}
