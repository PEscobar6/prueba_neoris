package com.neoris.app_transactions.controller;

import com.neoris.app_transactions.exception.CuentaNoEncontradaException;
import com.neoris.app_transactions.model.AccountStatementDTO;
import com.neoris.app_transactions.model.Transaction;
import com.neoris.app_transactions.service.AccountService;
import com.neoris.app_transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportsController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<AccountStatementDTO> generateAccountStatementReport(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        // Obtener las cuentas asociadas al cliente
        List<AccountStatementDTO> accountStatementDTOS = accountService.generateAccountStatementReport(clienteId, fechaInicio, fechaFin);

        if (!accountStatementDTOS.isEmpty()) {
            // Crear una lista para almacenar los informes
            List<AccountStatementDTO> reports = new ArrayList<>();

            for (AccountStatementDTO accounts : accountStatementDTOS) {
                // Obtener transacciones de la cuenta en el rango de fechas especificado
                List<Transaction> transactions = transactionService.getAccountTransactions(accounts.getNumeroCuenta(), fechaInicio, fechaFin);

                // Calcular el saldo de la cuenta hasta la fecha de fin
                BigDecimal accountBalance = transactionService.calculateAccountBalance(accounts.getNumeroCuenta(), fechaFin);

                // Crear y agregar el DTO del estado de cuenta a la lista de informes
                reports.add(new AccountStatementDTO(accountService.findByNumeroCuenta(accounts.getNumeroCuenta()), transactions));
            }

            return reports;
        } else {
            // Manejar el caso en el que no se encuentren cuentas asociadas al cliente
            throw new CuentaNoEncontradaException("No se encontraron cuentas asociadas al cliente con ID: " + clienteId);
        }
    }
}
