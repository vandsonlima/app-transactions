package com.example.apptransactions.controller;

import com.example.apptransactions.domain.Account;
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
}
