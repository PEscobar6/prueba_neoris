package com.neoris.app_transactions.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class TransactionDTO {
    private LocalDate fecha;
    private Long cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;

    public TransactionDTO(Account account, Transaction transaction) {
        this.fecha = transaction.getFecha();
        this.cliente = account.getClient_id();
        this.numeroCuenta = account.getNumero_cuenta();
        this.tipo = account.getTipo_cuenta();
        this.saldoInicial = account.getSaldo_inicial();
        this.estado = account.getEstado().equalsIgnoreCase("activo");
        this.movimiento = transaction.getValor();
        this.saldoDisponible = account.getSaldo_actual();
    }
}
