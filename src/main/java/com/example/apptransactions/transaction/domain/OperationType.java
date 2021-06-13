package com.example.apptransactions.transaction.domain;

import javax.persistence.*;

@Entity
@Table
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private SignOperation signOperation;

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
}
