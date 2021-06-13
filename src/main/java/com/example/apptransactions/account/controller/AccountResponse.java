package com.example.apptransactions.account.controller;

import com.example.apptransactions.account.domain.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResponse {

    @JsonProperty(value="account_id")
    private final Long accountId;

    @JsonProperty(value="document_number")
    private final String documentNumber;

    public AccountResponse(Account account) {
        this.accountId = account.getId();
        this.documentNumber = account.getDocumentNumber();
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "accountId=" + accountId +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}
