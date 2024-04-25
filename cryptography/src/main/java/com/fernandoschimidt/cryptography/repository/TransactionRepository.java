package com.fernandoschimidt.cryptography.repository;

import com.fernandoschimidt.cryptography.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
