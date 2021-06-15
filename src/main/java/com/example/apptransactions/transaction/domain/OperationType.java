package com.example.apptransactions.transaction.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private SignOperation signOperation;

    @Deprecated
    public OperationType() {
    }

    public OperationType(Long id, String description, SignOperation signOperation) {
        this.id = id;
        this.description = description;
        this.signOperation = signOperation;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public SignOperation getSignOperation() {
        return signOperation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationType that = (OperationType) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && signOperation == that.signOperation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, signOperation);
    }
}
