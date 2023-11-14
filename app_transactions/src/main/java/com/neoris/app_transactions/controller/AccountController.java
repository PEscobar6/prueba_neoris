package com.neoris.app_transactions.controller;

import com.neoris.app_transactions.model.Account;
import com.neoris.app_transactions.model.AccountStatementDTO;
import com.neoris.app_transactions.model.Transaction;
import com.neoris.app_transactions.service.AccountService;
import com.neoris.app_transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account ){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.saveAccount(account));
    }

    @GetMapping
    public ResponseEntity<Page<Account>> getAllAccounts(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination ){
        return ResponseEntity.ok(accountService.getAllAccount(page, size, enablePagination));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable("id") Long id) {
        Optional<Account> optionalAccount = accountService.findById(id);

        if (optionalAccount.isPresent()) {
            return ResponseEntity.ok(optionalAccount.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<Account> updateClient(Account account ){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.editAccount(account));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClient( @PathVariable("id") Long id ){
        accountService.deleteAccount(id);
        return ResponseEntity.ok(accountService.existById(id));
    }

    @GetMapping("/reportes")
    public ResponseEntity<AccountStatementDTO> generateReport(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            @RequestParam("clienteId") Long clienteId) {

        // Lógica para generar el reporte
        AccountStatementDTO report = accountService.generateAccountStatementReport(clienteId, fechaInicio, fechaFin);

        if (report != null) {
            return ResponseEntity.ok(report);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
