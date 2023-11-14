package com.neoris.app_transactions.service;

import com.neoris.app_transactions.exception.CuentaNoEncontradaException;
import com.neoris.app_transactions.exception.SaldoNoDisponibleException;
import com.neoris.app_transactions.model.Account;
import com.neoris.app_transactions.model.AccountStatementDTO;
import com.neoris.app_transactions.model.Transaction;
import com.neoris.app_transactions.model.TransactionDTO;
import com.neoris.app_transactions.repository.IAccountRepository;
import com.neoris.app_transactions.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private ITransactionRepository iTransactionRepository;

    @Autowired
    private IAccountRepository iAccountRepository;

    public Transaction saveTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("El movimiento no pudo ser creado.");
        }
        transaction.setFecha(LocalDate.now());

        Optional<Account> optionalAccount = iAccountRepository.findById(transaction.getAccount_id());
        if (optionalAccount.isPresent()) {

            BigDecimal saldoActual = getSaldoActual(transaction.getAccount_id());

            BigDecimal movimientosTotales = getSumValor(transaction.getAccount_id());

            BigDecimal nuevoSaldo = saldoActual.add(transaction.getValor());
            nuevoSaldo.add(movimientosTotales);

            if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
                throw new SaldoNoDisponibleException("Saldo no disponible");
            }

            optionalAccount.get().setSaldo_actual(nuevoSaldo);
            iAccountRepository.save(optionalAccount.get());

            transaction.setSaldo(nuevoSaldo);

            return iTransactionRepository.save(transaction);
        } else {
            throw new IllegalArgumentException("Cuenta no encontrada para el movimiento.");
        }
    }

    public List<TransactionDTO> getAllTransaction(Integer page, Integer size, Boolean enablePagination ) throws Exception {
        Page<Transaction> transactions = iTransactionRepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged());

        if (!transactions.getContent().isEmpty()) {
            List<TransactionDTO> transactionDTOS = new ArrayList<>();

            for (Transaction transaction : transactions) {
                Optional<Account> account = iAccountRepository.findById(transaction.getAccount_id());
                TransactionDTO transactionDTO = new TransactionDTO(account.get(), transaction);
                transactionDTOS.add(transactionDTO);
            }

            return transactionDTOS;
        } else {
            // Manejar el caso en el que no se encuentren cuentas asociadas al cliente
            throw new Exception("No se encontraron transacciones");
        }
    }

    public Optional<Transaction> findById(Long id ){
        return iTransactionRepository.findById(id);
    }

    public void deleteTransaction( Long id ){
        iTransactionRepository.deleteById(id);
    }

    public Transaction editTransaction(Transaction transaction){
        if (transaction.getMovimiento_id() != null && iTransactionRepository.existsById(transaction.getMovimiento_id())){
            return iTransactionRepository.save(transaction);
        }
        return transaction;
    }

    public Object existById( Long id ) {
        return iTransactionRepository.existsById(id);
    }

    private BigDecimal getSaldoActual(Long accountId) {
        return iAccountRepository.findSaldoInicialByAccountId(accountId);
    }

    private BigDecimal getSumValor(Long accountId) {
        return iTransactionRepository.calculateValorByAccountId(accountId);
    }

    public List<Transaction> getAccountTransactions(String numeroCuenta, LocalDate startDate, LocalDate endDate) {
        // Implementación para obtener transacciones de una cuenta en un rango de fechas
        // Utiliza iTransactionRepository u otro método según tu lógica
        Long accountId = iAccountRepository.findAccountIdByNumeroCuenta(numeroCuenta);
        return iTransactionRepository.findByAccount_IdAndFechaBetween(accountId, startDate, endDate);
    }

    public BigDecimal calculateAccountBalance(String numeroCuenta, LocalDate startDate) {
        // Implementación para calcular el saldo de una cuenta hasta una fecha específica
        // Utiliza iTransactionRepository u otro método según tu lógica
        Long accountId = iAccountRepository.findAccountIdByNumeroCuenta(numeroCuenta);
        return iTransactionRepository.calculateAccountBalance(accountId, startDate);
    }
}
