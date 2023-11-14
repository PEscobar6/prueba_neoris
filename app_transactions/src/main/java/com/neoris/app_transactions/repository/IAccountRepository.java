package com.neoris.app_transactions.repository;

import com.neoris.app_transactions.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.client_id = :clientId")
    List<Account> findAccountsByClientId(Long clientId);

    @Query("SELECT COALESCE(SUM(a.saldo_actual), 0) FROM Account a WHERE a.cuenta_id  = :accountId")
    BigDecimal findSaldoInicialByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT a.cuenta_id FROM Account a WHERE a.numero_cuenta = :numero_cuenta")
    Long findAccountIdByNumeroCuenta(String numero_cuenta);

    @Query("SELECT a FROM Account a WHERE a.numero_cuenta = :numero_cuenta")
    Account findAccountByNumeroCuenta(String numero_cuenta);
}
