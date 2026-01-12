package com.barLoyalty.gateway.repository;

import com.barLoyalty.gateway.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Ne va ajuta sa vedem istoricul unui user specific
    List<Transaction> findByUserId(Long userId);
}
