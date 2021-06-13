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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

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
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest){
        logger.info("including new account", accountRequest);

        Account account = accountRequest.toModel();
        entityManager.persist(account);
        return ResponseEntity.ok().body(new AccountResponse(account)
                .add(linkTo(methodOn(AccountController.class).getAccount(account.getId()))
                .withRel("/accounts/{id}")));
    }
}