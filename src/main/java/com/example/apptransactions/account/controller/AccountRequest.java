package com.example.apptransactions.account.controller;

import com.example.apptransactions.account.domain.Account;
import com.example.apptransactions.commons.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AccountRequest {

    @JsonProperty("document_number")
    @NotBlank
    @UniqueValue(domainClass = Account.class, fieldName = "documentNumber")
    private String document;

    public AccountRequest() {
    }

    public AccountRequest(@NotBlank String document) {
        this.document = document;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }


    public Account toModel() {
        return new Account(this.document);
    }
}
