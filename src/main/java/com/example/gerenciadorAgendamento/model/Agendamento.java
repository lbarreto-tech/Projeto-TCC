package com.example.gerenciadorAgendamento.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidade que representa uma solicitação de reserva de espaço.
 */
@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @Column(nullable = false)
    private String horario;

    @Column(name = "nome_requerente", nullable = false)
    private String nomeRequerente;

    @Column(name = "cpf_cnpj", nullable = false)
    private String cpfCnpj;

    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(name = "publico_estimado", nullable = false)
    private Integer publicoEstimado;

    @Column(name = "descricao_evento", nullable = false, columnDefinition = "TEXT")
    private String descricaoEvento;

    @Column(name = "documento_frente_path")
    private String documentoFrentePath;

    @Column(name = "documento_verso_path")
    private String documentoVersoPath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AgendamentoStatus status;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    protected void aoSalvar() {
        this.criadoEm = LocalDateTime.now();
        if (this.status == null) {
            this.status = AgendamentoStatus.PENDENTE;
        }
    }

    // ======================== Construtores ========================

    public Agendamento() {}

    // ======================== Getters e Setters ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getNomeRequerente() { return nomeRequerente; }
    public void setNomeRequerente(String nomeRequerente) { this.nomeRequerente = nomeRequerente; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Integer getPublicoEstimado() { return publicoEstimado; }
    public void setPublicoEstimado(Integer publicoEstimado) { this.publicoEstimado = publicoEstimado; }

    public String getDescricaoEvento() { return descricaoEvento; }
    public void setDescricaoEvento(String descricaoEvento) { this.descricaoEvento = descricaoEvento; }

    public String getDocumentoFrentePath() { return documentoFrentePath; }
    public void setDocumentoFrentePath(String documentoFrentePath) { this.documentoFrentePath = documentoFrentePath; }

    public String getDocumentoVersoPath() { return documentoVersoPath; }
    public void setDocumentoVersoPath(String documentoVersoPath) { this.documentoVersoPath = documentoVersoPath; }

    public AgendamentoStatus getStatus() { return status; }
    public void setStatus(AgendamentoStatus status) { this.status = status; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
