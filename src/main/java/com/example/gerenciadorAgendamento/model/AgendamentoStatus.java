package com.example.gerenciadorAgendamento.model;

/**
 * Status possíveis para um agendamento.
 */
public enum AgendamentoStatus {
    /** Solicitação enviada, aguardando análise da administração */
    PENDENTE,
    /** Solicitação aprovada pela administração */
    APROVADO,
    /** Solicitação rejeitada pela administração */
    REJEITADO
}
