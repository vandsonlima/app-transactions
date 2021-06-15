package com.example.apptransactions.account.domain;

import java.util.UUID;

public class AccountFactory {

    public Account getNewAccount(){
        return new Account(UUID.randomUUID().toString());
    }

    public Account newAccount(String documentNumber){
        return new Account(documentNumber);
    }
}
