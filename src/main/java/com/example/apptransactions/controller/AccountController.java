package com.example.apptransactions.controller;

import com.example.apptransactions.domain.Account;
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

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/accounts/{id}")
    public AccountResponse getAccount(@PathVariable @Valid Long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id))
                .map(AccountResponse::new)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity createAccount(@RequestBody @Valid AccountRequest accountRequest){
        entityManager.persist(accountRequest.toModel());
        return ResponseEntity.ok().build();
    }
}