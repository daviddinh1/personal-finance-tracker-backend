package org.dtd6003.personalfinancetrackerbackend.transaction.repo;

import org.dtd6003.personalfinancetrackerbackend.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUserId(Long userId);
}
