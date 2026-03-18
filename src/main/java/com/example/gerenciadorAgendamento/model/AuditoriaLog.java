package com.example.gerenciadorAgendamento.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidade de log de auditoria para registrar ações administrativas.
 */
@Entity
@Table(name = "logs_auditoria")
public class AuditoriaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String acao;

    @Column(nullable = false)
    private LocalDateTime momento;

    @Column(name = "agendamento_id")
    private Long agendamentoId;

    @PrePersist
    protected void aoSalvar() {
        this.momento = LocalDateTime.now();
    }

    // ======================== Construtores ========================

    public AuditoriaLog() {}

    public AuditoriaLog(String acao, Long agendamentoId) {
        this.acao = acao;
        this.agendamentoId = agendamentoId;
    }

    // ======================== Getters e Setters ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAcao() { return acao; }
    public void setAcao(String acao) { this.acao = acao; }

    public LocalDateTime getMomento() { return momento; }
    public void setMomento(LocalDateTime momento) { this.momento = momento; }

    public Long getAgendamentoId() { return agendamentoId; }
    public void setAgendamentoId(Long agendamentoId) { this.agendamentoId = agendamentoId; }
}
