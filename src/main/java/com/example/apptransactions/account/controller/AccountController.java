package com.example.apptransactions.account.controller;

import com.example.apptransactions.account.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("v1")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/accounts/{id}")
    public AccountResponse getAccount(@PathVariable @Valid Long id) {
        //todo hateoas
        return Optional.ofNullable(entityManager.find(Account.class, id))
                .map(AccountResponse::new)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity createAccount(@RequestBody @Valid AccountRequest accountRequest){
        Account account = accountRequest.toModel();
        logger.info("including new account", account);
        entityManager.persist(account);
        //todo: hateoas
        //todo: tratamento de retorno de erro 500 e de responsestatusException
        return ResponseEntity.ok(new AccountResponse(account));
    }
}