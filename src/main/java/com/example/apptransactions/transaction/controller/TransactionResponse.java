package com.example.apptransactions.transaction.controller;

import com.example.apptransactions.transaction.domain.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionResponse {
    @JsonProperty("account_id")
    private final Long accountId;

    @JsonProperty("operation_type_id")
    private final Long operationTypeId;

    private final BigDecimal amount;

    private Date eventDate;

    public TransactionResponse(Transaction transaction) {
        this.accountId = transaction.getAccount().getId();
        this.operationTypeId = transaction.getOperationType().getId();
        this.amount = transaction.getAmount();
        this.eventDate = transaction.getEventDate();
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getOperationTypeId() {
        return operationTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getEventDate() {
        return eventDate;
    }
}
