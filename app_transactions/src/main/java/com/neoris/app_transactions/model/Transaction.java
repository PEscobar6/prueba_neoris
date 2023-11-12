package com.neoris.app_transactions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimiento_id;
    private Date fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
}
