package com.example.apptransactions.account.controller;

import com.example.apptransactions.account.domain.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class AccountResponse extends RepresentationModel<AccountResponse> {

    @JsonProperty(value="account_id")
    private Long accountId;

    @JsonProperty(value="document_number")
    private String documentNumber;

    public AccountResponse() {
    }

    public AccountResponse(String documentNumber) {
        this.documentNumber = documentNumber;
    }

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
