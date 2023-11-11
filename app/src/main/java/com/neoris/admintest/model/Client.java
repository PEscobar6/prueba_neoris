package com.neoris.admintest.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity
@Table(name = "client")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;
    private Long usuario_id;
    private String contrasena;
    private String estado;

    public void setContrasena(String contrasena) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.contrasena = passwordEncoder.encode(contrasena);
    }
}
