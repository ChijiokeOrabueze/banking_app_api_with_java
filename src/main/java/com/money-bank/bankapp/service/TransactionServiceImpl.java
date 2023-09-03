package com.fcmb.bankapp.service;

import com.fcmb.bankapp.dto.AirtimeRequest;
import com.fcmb.bankapp.dto.AirtimeResponse;
import com.fcmb.bankapp.dto.TransferRequest;
import com.fcmb.bankapp.dto.TransferResponse;
import com.fcmb.bankapp.model.Account;
import com.fcmb.bankapp.model.AccountType;
import com.fcmb.bankapp.model.Transaction;
import com.fcmb.bankapp.model.TransactionType;
import com.fcmb.bankapp.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;


    @Override
    public TransferResponse doTransfer(TransferRequest transferRequest) {

        Account sourceAccount = accountService.findAccountById(transferRequest.getSourceAccount());

        Account destinationAccount = accountService.findAccountById(transferRequest.getDestinationAccount());

        Double[] rateAndDiscountCategory = calculateDiscountRate(sourceAccount);

        BigDecimal discountAmount = calculateDiscountAmount(rateAndDiscountCategory[0], transferRequest.getAmount(), rateAndDiscountCategory[0].intValue());

        accountService.transfer(sourceAccount, destinationAccount, transferRequest.getAmount(), discountAmount);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.TRANSFER)
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .rate(rateAndDiscountCategory[0])
                .discountedAmount(discountAmount)
                .amount(transferRequest.getAmount())
                .transactionDate(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);


        return TransferResponse.builder()
                .amount(transferRequest.getAmount())
                .sourceAccount(transferRequest.getSourceAccount())
                .destinationAccount(transferRequest.getDestinationAccount())
                .build();
    }

    @Override
    public AirtimeResponse buyAirtime(AirtimeRequest airtimeRequest) {

        Account sourceAccount = accountService.findAccountById(airtimeRequest.getSourceAccount());

        accountService.withDraw(sourceAccount, airtimeRequest.getAmount(), true);


        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.AIRTIME_PURCHASE)
                .sourceAccount(sourceAccount)
                .amount(airtimeRequest.getAmount())
                .transactionDate(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);
        return AirtimeResponse.builder()
                .amount(airtimeRequest.getAmount())
                .sourceAccount(airtimeRequest.getSourceAccount())
                .phoneNumber(airtimeRequest.getPhoneNumber())
                .networkProvider(airtimeRequest.getNetworkProvider())
                .build();
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId) {
        accountService.findAccountById(accountId);

        List<Transaction> userTransactionHistory = transactionRepository.findAllWhereSourceAccountIdEquals(accountId);

        return userTransactionHistory;

    }

//    @Override
//    public TransferResponse doTransfer(TransferRequest transferRequest) {
//
//        Account sourceAccount = accountService.findAccountById(transferRequest.getSourceAccount());
//
//        Account destinationAccount = accountService.findAccountById(transferRequest.getDestinationAccount());
//
//        if (sourceAccount.getBalance().intValue() < transferRequest.getAmount().intValue()){
//            throw new RuntimeException("Insufficient balance");
//        }
//
//        Double[] rateAndDiscountCategory = calculateDiscountRate(sourceAccount);
//
//        BigDecimal discountAmount = calculateDiscountAmount(rateAndDiscountCategory[0], transferRequest.getAmount(), rateAndDiscountCategory[0].intValue());
//
//        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferRequest.getAmount()).add(discountAmount));
//        destinationAccount.setBalance(sourceAccount.getBalance().add(transferRequest.getAmount()));
//
//        accountRepository.saveAll(Arrays.asList(sourceAccount, destinationAccount));
//        accountRepository.updateBalanceById(sourceAccount.getId(), sourceAccount.getBalance().subtract(transferRequest.getAmount()));
////
//        accountRepository.updateBalanceById(destinationAccount.getId(), sourceAccount.getBalance().add(transferRequest.getAmount()));
//
//
//        Transaction transaction = Transaction.builder()
//                .transactionType(TransactionType.TRANSFER)
//                .sourceAccount(sourceAccount)
//                .destinationAccount(destinationAccount)
//                .rate(rateAndDiscountCategory[0])
//                .discountedAmount(discountAmount)
//                .amount(transferRequest.getAmount())
//                .transactionDate(LocalDateTime.now())
//                .build();
//
//        transactionRepository.save(transaction);
//
//
//        return TransferResponse.builder()
//                .amount(transferRequest.getAmount())
//                .sourceAccount(transferRequest.getSourceAccount())
//                .destinationAccount(transferRequest.getDestinationAccount())
//                .build();
//    }

    private Double[] calculateDiscountRate (Account account){

        Double[] result = new Double[]{0.0, 1.0};
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime monthBeginning = LocalDateTime.of(today.getYear(), today.getMonth(), 1,0,0,0,0);

        List<Transaction> userMonthTransactions = transactionRepository.findAllWhereTransactionDateIsGte(monthBeginning);


        if ((account.getStartDate().getYear() - today.getYear() > 4) && userMonthTransactions.size() < 3){
            result[0] = 10.0;
        } else if (userMonthTransactions.size() > 3) {
            result[0] = account.getAccountType() == AccountType.BUSINESS ? 27.0 : 18.0;
            result[1] = account.getAccountType() == AccountType.BUSINESS ? 2.0 : 3.0;
        }


        return result;
    }

    private BigDecimal calculateDiscountAmount (Double rate, BigDecimal transactionAmount, Integer discountCategory){

        BigDecimal discountAmount = new BigDecimal(0);
        Double discountRate = rate/100;



        if (
                discountCategory == 1 ||
                        (discountCategory == 2 && transactionAmount.compareTo(new BigDecimal(150000)) > 0) ||
                        (discountCategory == 3 && transactionAmount.compareTo(new BigDecimal(50000)) > 0)
        ){
            discountAmount = new BigDecimal(discountRate).multiply(transactionAmount);
        }


        return discountAmount;
    }



}
