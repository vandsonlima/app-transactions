package com.example.apptransactions.transaction.repository;

import com.example.apptransactions.transaction.domain.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}
