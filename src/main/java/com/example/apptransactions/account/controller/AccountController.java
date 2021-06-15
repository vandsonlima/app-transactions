package com.example.apptransactions.account.controller;

import com.example.apptransactions.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController( AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{id}")
    public AccountResponse getAccount(@PathVariable @Valid Long id) {
        return accountService.getById(id)
                .map(AccountResponse::new)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest){
        logger.info("including new account", accountRequest);
        var account = accountService.save(accountRequest.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountResponse(account)
                .add(linkTo(methodOn(AccountController.class).getAccount(account.getId()))
                .withRel("/accounts/{id}")));
    }
}