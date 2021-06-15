package com.example.apptransactions.transaction.domain;

import com.example.apptransactions.account.domain.Account;
import com.example.apptransactions.account.domain.AccountFactory;

import java.math.BigDecimal;

public class TransactionFactory {

    AccountFactory accountFactory = new AccountFactory();
    OperationTypeFactory operationTypeFactory = new OperationTypeFactory();

    public Transaction getNewTransaction(){
        return new Transaction(accountFactory.getNewAccount(), operationTypeFactory.compraAVista(), new BigDecimal("100"));
    }

    public Transaction getTransactionBy(Account  account, OperationType operationType, BigDecimal bigDecimal) {
        return new Transaction(account, operationType, bigDecimal);
    }

}
