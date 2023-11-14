package com.neoris.app_transactions.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuenta_id;
    private String numero_cuenta;
    private String tipo_cuenta;
    private BigDecimal saldo_inicial;
    private BigDecimal saldo_actual;
    private String estado;
    private Long client_id;
}
