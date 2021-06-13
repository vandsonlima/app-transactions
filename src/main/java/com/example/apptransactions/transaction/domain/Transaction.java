package com.example.apptransactions.transaction.domain;

import com.example.apptransactions.account.domain.Account;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account account;
    @ManyToOne
    private OperationType operationType;
    @NotNull
    private BigDecimal amount;


    public Transaction() {
    }

    public Transaction(Account account, OperationType operationType, BigDecimal amount) {
        Assert.notNull(account, "Account must be not null");
        Assert.notNull(operationType, "OperationType must be not null");
        Assert.isTrue(amount.compareTo(new BigDecimal("0")) > 0, "Amount must be greater than zero");

        this.account = account;
        this.operationType = operationType;
        this.amount = operationType.getSignOperation().calcvalue(amount);
    }
}
