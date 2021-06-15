package com.example.apptransactions.transaction.service;

import com.example.apptransactions.GenericTest;
import com.example.apptransactions.account.domain.Account;
import com.example.apptransactions.account.domain.AccountFactory;
import com.example.apptransactions.account.repository.AccountRepository;
import com.example.apptransactions.transaction.controller.TransactionRequest;
import com.example.apptransactions.transaction.domain.OperationType;
import com.example.apptransactions.transaction.domain.OperationTypeFactory;
import com.example.apptransactions.transaction.repository.OperationTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionServiceTest extends GenericTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private OperationTypeRepository operationTypeRepository;

    private Account accountPersisted;
    private OperationType operationTypePersisted;

    private final AccountFactory accountFactory = new AccountFactory();
    private final OperationTypeFactory operationTypeFactory = new OperationTypeFactory();

    @BeforeEach
    void setUp() {
        accountPersisted = accountFactory.getNewAccount();
        accountPersisted = accountRepository.save(accountPersisted);

        operationTypePersisted = operationTypeFactory.compraAVista();
        operationTypeRepository.save(operationTypePersisted);
    }

    @DisplayName("create transaction with invalid account id")
    @Test
    void createTransactionWithInvalidAccount() {
        var transactionRequest = new TransactionRequest(Long.MAX_VALUE, operationTypePersisted.getId(), new BigDecimal(100));
        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class, () -> transactionService.create(transactionRequest));
        assertNotNull(argumentException);
    }

    @DisplayName("create transaction with invalid operation type id")
    @Test
    void createTransactionWithInvalidOperationType() {
        var transactionRequest = new TransactionRequest(accountPersisted.getId(), 0L, new BigDecimal(100));
        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class, () -> transactionService.create(transactionRequest));
        assertNotNull(argumentException);
    }
}