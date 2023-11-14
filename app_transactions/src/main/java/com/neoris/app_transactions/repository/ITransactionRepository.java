package com.neoris.app_transactions.repository;

import com.neoris.app_transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.account_id = :accountId AND t.fecha BETWEEN :startDate AND :endDate")
    List<Transaction> findByAccount_IdAndFechaBetween(@Param("accountId") Long accountId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transaction t WHERE t.account_id = :accountId AND t.fecha <= :startDate")
    BigDecimal calculateAccountBalance(@Param("accountId") Long accountId, @Param("startDate") LocalDate startDate);

    @Query("SELECT COALESCE(SUM(t.valor), 0) FROM Transaction t WHERE t.account_id  = :accountId")
    BigDecimal calculateValorByAccountId(@Param("accountId") Long accountId);
}
