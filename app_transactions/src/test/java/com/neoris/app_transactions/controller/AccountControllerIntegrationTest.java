package com.neoris.app_transactions.controller;

import com.neoris.app_transactions.model.Account;
import com.neoris.app_transactions.service.AccountService;
import com.neoris.app_transactions.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerIntegrationTest {
        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private AccountService accountService;

        @Autowired
        private TransactionService transactionService;

    @Test
    void testSaveAccount() {
        String baseUrl = "http://localhost:" + port + "/accounts";


        Account account = new Account();

        ResponseEntity<Account> responseEntity = restTemplate.postForEntity(baseUrl, account, Account.class);

        // Verificar la respuesta
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getCuenta_id());

        // Verificar la persistencia de la cuenta en la base de datos
        Long accountId = responseEntity.getBody().getCuenta_id();
        Optional<Account> savedAccount = accountService.findById(accountId);
        assertTrue(savedAccount.isPresent());
        assertEquals(account.getNumero_cuenta(), savedAccount.get().getNumero_cuenta());
    }

}
