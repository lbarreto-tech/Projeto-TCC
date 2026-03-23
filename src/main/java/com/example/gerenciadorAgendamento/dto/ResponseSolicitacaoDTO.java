package com.example.gerenciadorAgendamento.dto;

public class ResponseSolicitacaoDTO     {
    private Long id;
    private String message;


    public ResponseSolicitacaoDTO() {
    }

    public ResponseSolicitacaoDTO(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
