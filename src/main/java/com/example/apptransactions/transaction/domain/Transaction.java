package com.example.apptransactions.transaction.domain;

import com.example.apptransactions.account.domain.Account;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    @Deprecated
    public Transaction() {
    }

    public Transaction(Account account, OperationType operationType, BigDecimal amount) {
        Assert.notNull(account, "Account must be not null");
        Assert.notNull(operationType, "OperationType must be not null");
        Assert.notNull(amount, "Amount must be not null");
        Assert.isTrue(amount.compareTo(new BigDecimal("0")) > 0, "Amount must be greater than zero");

        this.account = account;
        this.operationType = operationType;
        this.amount = operationType.getSignOperation().calcvalue(amount);
        eventDate = new Date();
    }

    public Account getAccount() {
        return account;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getEventDate() {
        return eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(account, that.account) && Objects.equals(operationType, that.operationType) && Objects.equals(amount, that.amount) && Objects.equals(eventDate, that.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, operationType, amount, eventDate);
    }
}
