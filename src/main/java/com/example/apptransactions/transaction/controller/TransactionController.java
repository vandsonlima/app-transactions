package com.example.apptransactions.transaction.controller;

import com.example.apptransactions.transaction.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> executeTransaction(@RequestBody @Valid TransactionRequest transactionRequest){
        logger.info("persisting a new transaction", transactionRequest);
        var transaction = transactionService.create(transactionRequest);
        return ResponseEntity.ok().body(new TransactionResponse(transaction));
    }
}
