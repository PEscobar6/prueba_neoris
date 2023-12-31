package com.neoris.app_transactions.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountStatementDTO {
    private Long cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;

    public AccountStatementDTO(Account account, List<Transaction> transactions) {
        this.cliente = account.getClient_id();
        this.numeroCuenta = account.getNumero_cuenta();
        this.tipo = account.getTipo_cuenta();
        this.saldoInicial = account.getSaldo_inicial();
        this.estado = account.getEstado().equalsIgnoreCase("activo");
        this.movimiento = calculateTotalMovement(transactions);
        this.saldoDisponible = account.getSaldo_actual();
    }

    private BigDecimal calculateTotalMovement(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}