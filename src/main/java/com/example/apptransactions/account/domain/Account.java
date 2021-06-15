package com.example.apptransactions.account.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, name = "document_number", nullable = false)
    private String documentNumber;

    public Account(@NotBlank String documentNumber) {
        Assert.hasLength(documentNumber, "documentNumber must be informed");
        this.documentNumber = documentNumber;
    }

    @Deprecated
    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(documentNumber, account.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentNumber);
    }
}
