package com.example.apptransactions.transaction.controller;

import com.example.apptransactions.account.domain.Account;
import com.example.apptransactions.commons.validation.ExistsId;
import com.example.apptransactions.transaction.domain.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class TransactionRequest {

    @NotNull
    @JsonProperty("account_id")
    @ExistsId(domainClass = Account.class, fieldName = "id")
    private final Long accountId;

    @NotNull
    @PositiveOrZero
    @ExistsId(domainClass = OperationType.class, fieldName = "id")
    @JsonProperty("operation_type_id")
    private final Long operationTypeId;

    @NotNull
    @Positive
    private final BigDecimal amount;

    public TransactionRequest(@NotNull Long accountId, @NotNull @PositiveOrZero Long operationTypeId, @NotNull @Positive BigDecimal amount) {
        this.accountId = accountId;
        this.operationTypeId = operationTypeId;
        this.amount = amount;
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

}
