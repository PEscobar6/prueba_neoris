package com.neoris.app_transactions.service;

import com.neoris.app_transactions.model.Account;
import com.neoris.app_transactions.model.AccountStatementDTO;
import com.neoris.app_transactions.model.Transaction;
import com.neoris.app_transactions.repository.IAccountRepository;
import com.neoris.app_transactions.repository.ITransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private IAccountRepository accountRepository;

    @Mock
    private ITransactionRepository transactionRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateAccountStatementReport() {

        Long clienteId = 1L;
        LocalDate fechaInicio = LocalDate.now().minusDays(30);
        LocalDate fechaFin = LocalDate.now();

        // Mock de cuentas asociadas al cliente
        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account();
        account1.setCuenta_id(1L);
        accounts.add(account1);

        when(accountRepository.findAccountsByClientId(clienteId)).thenReturn(accounts);


        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();


        transactions.add(transaction1);

        when(transactionRepository.findByAccount_IdAndFechaBetween(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(transactions);


        List<AccountStatementDTO> accountStatements = accountService.generateAccountStatementReport(clienteId, fechaInicio, fechaFin);


        verify(accountRepository, times(1)).findAccountsByClientId(clienteId);
        verify(transactionRepository, times(1)).findByAccount_IdAndFechaBetween(anyLong(), any(LocalDate.class), any(LocalDate.class));


        assertNotNull(accountStatements);
        assertFalse(accountStatements.isEmpty());
        assertEquals(1, accountStatements.size());
    }

}
