package com.example.apptransactions.transaction.controller;

import com.example.apptransactions.transaction.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<String> executeTransaction(@RequestBody @Valid TransactionRequest transactionRequest){
        //todo: retorno da operacao - TransactionResponse c hateoas
        //todo:hateoas
        logger.info("persit a new transaction", transactionRequest);
        Transaction transaction = transactionRequest.toModel(entityManager);
        entityManager.persist(transaction);
        return ResponseEntity.ok("Transação efetuada com sucesso");
    }
}
