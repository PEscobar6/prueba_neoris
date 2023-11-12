package com.neoris.app_transactions.service;

import com.neoris.app_transactions.model.Account;
import com.neoris.app_transactions.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    public Account saveAccount( Account account ){
        if (account == null) {
            throw new IllegalArgumentException("La cuenta no pudo ser creada.");
        }
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
        if (account.getAccount_id() != null && iAccountRepository.existsById(account.getAccount_id())){
            return iAccountRepository.save(account);
        }
        return account;
    }

    public Object existById( Long id ) {
        return iAccountRepository.existsById(id);
    }
}
