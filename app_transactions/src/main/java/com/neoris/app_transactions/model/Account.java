package com.neoris.app_transactions.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    private String numero_cuenta;
    private String tipo_cuenta;
    private Float saldo_inicial;
    private String estado;
}
