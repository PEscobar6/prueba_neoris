package com.neoris.app_transactions.service;

import com.neoris.app_transactions.exception.CuentaNoEncontradaException;
import com.neoris.app_transactions.model.Account;
import com.neoris.app_transactions.model.AccountStatementDTO;
import com.neoris.app_transactions.model.Transaction;
import com.neoris.app_transactions.repository.IAccountRepository;
import com.neoris.app_transactions.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private ITransactionRepository iTransactionRepository;

    public Account saveAccount( Account account ){
        if (account == null) {
            throw new IllegalArgumentException("La cuenta no pudo ser creada.");
        }
        account.setSaldo_actual(account.getSaldo_inicial());
        return iAccountRepository.save(account);
    }

    public Page<Account> getAllAccount( Integer page, Integer size, Boolean enablePagination ){
        return iAccountRepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged());
    }

    public Optional<Account> findById( Long id ){
        return iAccountRepository.findById(id);
    }

    public void deleteAccount( Long id ){
        iAccountRepository.deleteById(id);
    }

    public Account editAccount(Account account){
        if (account.getCuenta_id() != null && iAccountRepository.existsById(account.getCuenta_id())){
            return iAccountRepository.save(account);
        }
        return account;
    }

    public Object existById( Long id ) {
        return iAccountRepository.existsById(id);
    }

    public AccountStatementDTO generateAccountStatementReport(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        // Obtener la cuenta asociada al cliente
        Optional<Account> optionalAccount = iAccountRepository.findByClientId(clienteId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            // Obtener transacciones de la cuenta en el rango de fechas especificado
            List<Transaction> transactions = iTransactionRepository.findByAccount_IdAndFechaBetween(account.getCuenta_id(), fechaInicio, fechaFin);

            // Calcular el saldo de la cuenta hasta la fecha de fin
            BigDecimal accountBalance = iTransactionRepository.calculateAccountBalance(account.getCuenta_id(), fechaFin);

            // Crear y retornar el DTO del estado de cuenta
            return new AccountStatementDTO(account, transactions, accountBalance);
        } else {
            // Manejar el caso en el que no se encuentre la cuenta asociada al cliente
            throw new CuentaNoEncontradaException("No se encontró la cuenta asociada al cliente con ID: " + clienteId);
        }
    }
}
