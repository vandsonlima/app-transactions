package com.example.apptransactions.transaction.domain;

import com.example.apptransactions.GenericTest;
import com.example.apptransactions.account.domain.AccountFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest extends GenericTest {

    private final TransactionFactory transactionFactory = new TransactionFactory();
    private final AccountFactory accountFactory = new AccountFactory();
    private final OperationTypeFactory operationTypeFactory = new OperationTypeFactory();

    @Test
    @DisplayName("transaction must have an not null account id")
    public void transctionsmustbeNotNullAccount(){
        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class, () -> {
            var transaction = transactionFactory.getTransactionBy(null, operationTypeFactory.compraAVista(), new BigDecimal("0.34"));
        });
    }

    @Test
    @DisplayName("transaction must have an not null operationType id")
    public void transctionsmustbeNotNullOperationalType(){
        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class, () -> {
            var transaction = transactionFactory.getTransactionBy(accountFactory.getNewAccount(), null, new BigDecimal("0.34"));
        });
    }

    @Test
    @DisplayName("transaction must have an positive amount")
    public void transctionsmustHavePositiveAmount(){
        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class, () -> {
            var transaction = transactionFactory.getTransactionBy(accountFactory.getNewAccount(), operationTypeFactory.compraAVista(), new BigDecimal("0.34").negate());
        });
    }

    @Test
    @DisplayName("transaction must have an not null amount")
    public void transctionsmustHaveNotNullAmount(){
        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class, () -> {
            var transaction = transactionFactory.getTransactionBy(accountFactory.getNewAccount(), operationTypeFactory.compraAVista(), null);
        });
    }

}