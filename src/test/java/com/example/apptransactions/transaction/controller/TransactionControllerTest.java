package com.example.apptransactions.transaction.controller;

import com.example.apptransactions.GenericTest;
import com.example.apptransactions.account.domain.AccountFactory;
import com.example.apptransactions.account.repository.AccountRepository;
import com.example.apptransactions.commons.exception.ValidationErrorsOutput;
import com.example.apptransactions.transaction.domain.OperationTypeFactory;
import com.example.apptransactions.transaction.repository.OperationTypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionControllerTest extends GenericTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationTypeRepository operationTypeRepository;

    private final TransactionRequestFactory transactionRequestFactory = new TransactionRequestFactory();
    private final AccountFactory accountFactory = new AccountFactory();
    private final OperationTypeFactory operationTypeFactory = new OperationTypeFactory();

    @Test
    @DisplayName("transaction must have be an account id")
    void executeTransactionWithoutAccoundId() throws Exception {
        var response = mockMvc.perform(
                post("/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequestFactory.getRequestNoAccountId())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        ValidationErrorsOutput output = objectMapper.readerFor(ValidationErrorsOutput.class).readValue(response.getContentAsString());
        assertNotNull(output);
    }

    @Test
    @DisplayName("transaction must have be an operationType id")
    void executeTransactionWithoutOperationTypeId() throws Exception {
        var response = mockMvc.perform(
                post("/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequestFactory.getRequestNoOperationId())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        ValidationErrorsOutput output = objectMapper.readerFor(ValidationErrorsOutput.class).readValue(response.getContentAsString());
        assertNotNull(output);
    }

    @Test
    @DisplayName("transaction must have be an amount")
    void executeTransactionWithoutAmount() throws Exception {
        var response = mockMvc.perform(
                post("/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequestFactory.getRequestNoAmount())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        ValidationErrorsOutput output = objectMapper.readerFor(ValidationErrorsOutput.class).readValue(response.getContentAsString());
        assertNotNull(output);
    }

    @Test
    @DisplayName("transaction must have be an positive amount")
    void executeTransactionWithNegativeAmount() throws Exception {
        var response = mockMvc.perform(
                post("/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequestFactory.getAmountNegative())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();

        ValidationErrorsOutput output = objectMapper.readerFor(ValidationErrorsOutput.class).readValue(response.getContentAsString());
        assertNotNull(output);
    }

    @Test
    @DisplayName("transaction must have been created")
    void executeTransaction() throws Exception {
        var account = accountFactory.getNewAccount();
        accountRepository.save(account);

        var operationalType = operationTypeFactory.compraParcelada();
        operationTypeRepository.save(operationalType);

        var amount = new BigDecimal("100");

        var transactionRequest = transactionRequestFactory.getTransactionRequest(account.getId(), operationalType.getId(), amount);

        var response = mockMvc.perform(
                post("/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn().getResponse();

        TransactionResponse transactionResponse = objectMapper.readerFor(TransactionResponse.class).readValue(response.getContentAsString());

        assertEquals(transactionRequest.getAccountId(), transactionResponse.getAccountId());
        assertEquals(transactionRequest.getOperationTypeId(), transactionResponse.getOperationTypeId());
        assertEquals(operationalType.getSignOperation().calcvalue(amount).toString(), transactionResponse.getAmount().toString());
    }

}