package com.example.apptransactions.account.domain;

import com.example.apptransactions.GenericTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest extends GenericTest {

    AccountFactory accountFactory = new AccountFactory();

    @Test
    @DisplayName("Account deve possuir um document_number")
    public void createAccountWithoutDocumentNumber(){
        assertThrows(IllegalArgumentException.class, () -> {
            var account = accountFactory.newAccount(null);
        });
    }

}