package com.example.apptransactions.account.domain;

import java.util.UUID;

public class AccountFactory {

    public Account getNewAccount(){
        var account = new Account(UUID.randomUUID().toString());
        account.setId(1L);
        return account;
    }

    public Account newAccount(String documentNumber){
        return new Account(documentNumber);
    }
}
