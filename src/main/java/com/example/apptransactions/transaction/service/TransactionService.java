package com.example.apptransactions.transaction.service;

import com.example.apptransactions.account.repository.AccountRepository;
import com.example.apptransactions.transaction.controller.TransactionRequest;
import com.example.apptransactions.transaction.domain.Transaction;
import com.example.apptransactions.transaction.repository.OperationTypeRepository;
import com.example.apptransactions.transaction.repository.TransactionsRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    private final TransactionsRepository transactionsRepository;

    private final AccountRepository accountRepository;

    private final OperationTypeRepository operationTypeRepository;

    public TransactionService(TransactionsRepository transactionsRepository, AccountRepository accountRepository, OperationTypeRepository operationTypeRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountRepository = accountRepository;
        this.operationTypeRepository = operationTypeRepository;
    }

    public Transaction create(TransactionRequest transactionRequest) {

        var account = accountRepository.findById(transactionRequest.getAccountId())
                .orElseThrow(IllegalArgumentException::new);

        var operationType = operationTypeRepository.findById(transactionRequest.getOperationTypeId())
                .orElseThrow(IllegalArgumentException::new);

        var transaction = new Transaction(account, operationType, transactionRequest.getAmount());
        transactionsRepository.save(transaction);
        return transaction;
    }
}
