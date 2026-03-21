package com.example.gerenciadorAgendamento.service;

import com.example.gerenciadorAgendamento.model.Agendamento;
import com.example.gerenciadorAgendamento.model.AuditoriaLog;
import com.example.gerenciadorAgendamento.repository.AgendamentoRepository;
import com.example.gerenciadorAgendamento.repository.AuditoriaLogRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LimpezaAgendamentoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LimpezaAgendamentoService.class);
    private static final String ACAO_REMOCAO_EXPIRADA = "AGENDAMENTO_REMOVIDO_POR_EXPIRACAO";

    // ======================== Atributos ========================

    private final AgendamentoRepository agendamentoRepository;
    private final AuditoriaLogRepository auditoriaLogRepository;

    // ======================== Construtores ========================

    public LimpezaAgendamentoService(AgendamentoRepository agendamentoRepository,
                                     AuditoriaLogRepository auditoriaLogRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.auditoriaLogRepository = auditoriaLogRepository;
    }

    // ======================== Métodos de Negócio ========================

    @Scheduled(cron = "0 5 0 * * *")
    public void executarLimpezaAgendada() {
        removerAgendamentosExpirados(LocalDate.now());
    }

    public void removerAgendamentosExpirados(LocalDate hoje) {
        List<Agendamento> agendamentosExpirados = agendamentoRepository.buscarAgendamentosAntesData(hoje);

        for (Agendamento agendamento : agendamentosExpirados) {
            removerAgendamentoSeArquivosForemExcluidos(agendamento);
        }
    }

    private void removerAgendamentoSeArquivosForemExcluidos(Agendamento agendamento) {
        if (!excluirArquivosAssociados(agendamento)) {
            return;
        }

        agendamentoRepository.delete(agendamento);
        auditoriaLogRepository.save(new AuditoriaLog(ACAO_REMOCAO_EXPIRADA, agendamento.getId()));
    }

    private boolean excluirArquivosAssociados(Agendamento agendamento) {
        return excluirArquivo(agendamento.getDocumentoFrentePath(), agendamento.getId())
                && excluirArquivo(agendamento.getDocumentoVersoPath(), agendamento.getId());
    }

    private boolean excluirArquivo(String caminhoArquivo, Long agendamentoId) {
        if (caminhoArquivo == null || caminhoArquivo.isBlank()) {
            return true;
        }

        try {
            Files.deleteIfExists(Path.of(caminhoArquivo));
            return true;
        } catch (IOException | InvalidPathException | SecurityException exception) {
            LOGGER.error("Falha ao excluir arquivo do agendamento {}", agendamentoId, exception);
            return false;
        }
    }
}
