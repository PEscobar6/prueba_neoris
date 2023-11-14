package com.neoris.app_transactions.model;

import java.math.BigDecimal;
import java.util.List;

public class AccountStatementDTO {
    private Account account;
    private List<Transaction> transactions;
    private BigDecimal accountBalance;

    public AccountStatementDTO(Account account, List<Transaction> transactions, BigDecimal accountBalance) {
        this.account = account;
        this.transactions = transactions;
        this.accountBalance = accountBalance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
