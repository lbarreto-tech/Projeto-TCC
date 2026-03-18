package com.example.gerenciadorAgendamento.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade que representa o usuário administrador do sistema.
 */
@Entity
@Table(name = "usuarios")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void aoSalvar() {
        this.criadoEm = LocalDateTime.now();
    }

    // ======================== Construtores ========================

    public Administrador() {}

    public Administrador(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // ======================== Getters e Setters ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
