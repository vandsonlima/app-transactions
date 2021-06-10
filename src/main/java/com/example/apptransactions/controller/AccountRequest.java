package com.example.apptransactions.controller;

import com.example.apptransactions.domain.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

    @JsonProperty("document_number")
    private String document;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public AccountRequest() {
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
