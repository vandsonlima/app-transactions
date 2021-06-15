package com.example.apptransactions.account.controller;

import com.example.apptransactions.GenericTest;
import com.example.apptransactions.account.domain.AccountFactory;
import com.example.apptransactions.account.service.AccountService;
import com.example.apptransactions.commons.exception.ValidationErrorsOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends GenericTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AccountService accountService;

    AccountFactory accountFactory = new AccountFactory();

    @Test
    @DisplayName("create an acccount with an document number")
    void createAccountWithDocument() throws Exception {
        var accountResponse = new AccountResponse(UUID.randomUUID().toString());

        var response = mockMvc.perform(
          post("/v1/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(accountResponse)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated())
            .andReturn().getResponse();

        AccountResponse accountsResponse = objectMapper.readerFor(AccountResponse.class).readValue(response.getContentAsString());
        assertEquals(accountsResponse.getDocumentNumber(), accountResponse.getDocumentNumber());
    }

    @Test
    @DisplayName("create an acccount without an document number")
    void createAccountWithoutDocument() throws Exception {
        var response = mockMvc.perform(
                post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new AccountRequest())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        ValidationErrorsOutput output = objectMapper.readerFor(ValidationErrorsOutput.class).readValue(response.getContentAsString());
        assertNotNull(output);
    }

    @Test
    @DisplayName("test create an acccount with same document number")
    void createAccountWithAnEqualDocument() throws Exception {
        var account = accountFactory.newAccount(UUID.randomUUID().toString());
        accountService.save(account);

        var accountRequest = new AccountRequest(account.getDocumentNumber());

        var response = mockMvc.perform(
                post("/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();


        ValidationErrorsOutput output = objectMapper.readerFor(ValidationErrorsOutput.class).readValue(response.getContentAsString());
        assertNotNull(output);
    }

    @Test
    @DisplayName("get a registered account")
    void getAnRegisteredAccount() throws Exception {
        var account = accountFactory.newAccount(UUID.randomUUID().toString());
        accountService.save(account);

        var response = mockMvc.perform(
                get("/v1/accounts/{id}", account.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        AccountResponse accountResponse = objectMapper.readerFor(AccountResponse.class).readValue(response.getContentAsString());
        assertNotNull(accountResponse);
        assertEquals(accountResponse.getDocumentNumber(), account.getDocumentNumber());
    }

}
