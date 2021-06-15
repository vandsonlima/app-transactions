package com.example.apptransactions.transaction.controller;

import com.example.apptransactions.account.domain.AccountFactory;
import com.example.apptransactions.transaction.domain.OperationTypeFactory;

import java.math.BigDecimal;

public class TransactionRequestFactory {
    private final OperationTypeFactory operationTypeFactory = new OperationTypeFactory();
    private final AccountFactory accountFactory = new AccountFactory();

    public TransactionRequest getRequestNoAccountId(){
        return new TransactionRequest(null, operationTypeFactory.compraParcelada().getId(), new BigDecimal("100"));
    }

    public TransactionRequest getRequestNoOperationId(){
        return new TransactionRequest(accountFactory.getNewAccount().getId(), null, new BigDecimal("100"));
    }

    public TransactionRequest getRequestNoAmount(){
        return new TransactionRequest(accountFactory.getNewAccount().getId(), operationTypeFactory.compraParcelada().getId(), null);
    }

    public TransactionRequest getAmountNegative(){
        return new TransactionRequest(accountFactory.getNewAccount().getId(), operationTypeFactory.compraParcelada().getId(), new BigDecimal("100").negate());
    }

    public TransactionRequest getTransactionRequest(Long idAccount, Long operationTypeId, BigDecimal amount) {
        return new TransactionRequest(idAccount, operationTypeId, amount);
    }
}
