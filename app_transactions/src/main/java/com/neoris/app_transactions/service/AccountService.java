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

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Account findByNumeroCuenta( String numeroCuenta ){
        return iAccountRepository.findAccountByNumeroCuenta(numeroCuenta);
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

    public List<AccountStatementDTO> generateAccountStatementReport(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        // Obtener la lista de cuentas asociadas al cliente
        List<Account> accounts = iAccountRepository.findAccountsByClientId(clienteId);

        if (!accounts.isEmpty()) {
            List<AccountStatementDTO> accountStatements = new ArrayList<>();

            for (Account account : accounts) {
                // Obtener transacciones de la cuenta en el rango de fechas especificado
                List<Transaction> transactions = iTransactionRepository.findByAccount_IdAndFechaBetween(account.getCuenta_id(), fechaInicio, fechaFin);


                AccountStatementDTO accountStatementDTO = new AccountStatementDTO(account, transactions);
                accountStatements.add(accountStatementDTO);
            }

            return accountStatements;
        } else {
            // Manejar el caso en el que no se encuentren cuentas asociadas al cliente
            throw new CuentaNoEncontradaException("No se encontraron cuentas asociadas al cliente con ID: " + clienteId);
        }
    }
}
