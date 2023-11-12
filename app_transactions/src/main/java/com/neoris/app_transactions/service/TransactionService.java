package com.neoris.app_transactions.service;

import com.neoris.app_transactions.model.Transaction;
import com.neoris.app_transactions.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private ITransactionRepository iTransactionRepository;

    public Transaction saveTransaction(Transaction transaction ){
        if (transaction == null) {
            throw new IllegalArgumentException("El movimiento no pudo ser creado.");
        }
        return iTransactionRepository.save(transaction);
    }

    public Page<Transaction> getAllTransaction(Integer page, Integer size, Boolean enablePagination ){
        return iTransactionRepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged());
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
}
