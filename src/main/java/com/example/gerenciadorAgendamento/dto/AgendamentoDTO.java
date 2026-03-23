package com.example.gerenciadorAgendamento.dto;

import com.example.gerenciadorAgendamento.model.AgendamentoStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AgendamentoDTO {
    private Long id;
    private LocalDate dataEvento;
    private String horario;
    private String nomeRequerente;
    private String cpfCnpj;
    private String nomeResponsavel;
    private String endereco;
    private String bairro;
    private String email;
    private String telefone;
    private Integer publicoEstimado;
    private String descricaoEvento;
    private String documentoFrentePath;
    private String documentoVersoPath;
    private AgendamentoStatus status;
    private LocalDateTime criadoEm;


    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNomeRequerente() {
        return nomeRequerente;
    }

    public void setNomeRequerente(String nomeRequerente) {
        this.nomeRequerente = nomeRequerente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getPublicoEstimado() {
        return publicoEstimado;
    }

    public void setPublicoEstimado(Integer publicoEstimado) {
        this.publicoEstimado = publicoEstimado;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public String getDocumentoFrentePath() {
        return documentoFrentePath;
    }

    public void setDocumentoFrentePath(String documentoFrentePath) {
        this.documentoFrentePath = documentoFrentePath;
    }

    public String getDocumentoVersoPath() {
        return documentoVersoPath;
    }

    public void setDocumentoVersoPath(String documentoVersoPath) {
        this.documentoVersoPath = documentoVersoPath;
    }

    public AgendamentoStatus getStatus() {
        return status;
    }

    public void setStatus(AgendamentoStatus status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}


