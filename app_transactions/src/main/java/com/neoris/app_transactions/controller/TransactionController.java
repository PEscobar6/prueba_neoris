package com.neoris.app_transactions.controller;

import com.neoris.app_transactions.model.Transaction;
import com.neoris.app_transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction ){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.saveTransaction(transaction));
    }

    @GetMapping
    public ResponseEntity<Page<Transaction>> getAllTransactions(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination ){
        return ResponseEntity.ok(transactionService.getAllTransaction(page, size, enablePagination));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transaction> findTransactionById(@PathVariable("id") Long id) {
        Optional<Transaction> optionalTransaction = transactionService.findById(id);

        if (optionalTransaction.isPresent()) {
            return ResponseEntity.ok(optionalTransaction.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<Transaction> updateClient(Transaction transaction ){
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.editTransaction(transaction));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteClient( @PathVariable("id") Long id ){
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok(transactionService.existById(id));
    }
}
