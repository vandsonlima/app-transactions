package com.example.apptransactions.account.service;

import com.example.apptransactions.account.domain.Account;
import com.example.apptransactions.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class AccountService {


    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getById(@NotNull Long id) {
        Assert.notNull(id, "the account id must be not null");
        return accountRepository.findById(id);
    }

    public Account save(@NotNull Account account) {
        Assert.notNull(account, "the account must be not null");
        return accountRepository.save(account);
    }
}
