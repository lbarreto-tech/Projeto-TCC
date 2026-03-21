package com.example.gerenciadorAgendamento.service;

import com.example.gerenciadorAgendamento.model.Agendamento;
import com.example.gerenciadorAgendamento.model.AuditoriaLog;
import com.example.gerenciadorAgendamento.repository.AgendamentoRepository;
import com.example.gerenciadorAgendamento.repository.AuditoriaLogRepository;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.Scheduled;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LimpezaAgendamentoServiceTests {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private AuditoriaLogRepository auditoriaLogRepository;

    @InjectMocks
    private LimpezaAgendamentoService limpezaAgendamentoService;

    @TempDir
    Path diretorioTemporario;

    @Test
    void deveRemoverAgendamentoExpiradoComArquivosEAuditoria() throws IOException {
        // given
        LocalDate hoje = LocalDate.of(2026, 3, 21);
        Path documentoFrente = Files.createFile(diretorioTemporario.resolve("frente.pdf"));
        Path documentoVerso = Files.createFile(diretorioTemporario.resolve("verso.pdf"));
        Agendamento agendamento = criarAgendamento(1L, documentoFrente, documentoVerso);

        when(agendamentoRepository.buscarAgendamentosAntesData(hoje)).thenReturn(List.of(agendamento));

        // when
        limpezaAgendamentoService.removerAgendamentosExpirados(hoje);

        // then
        assertThat(Files.exists(documentoFrente)).isFalse();
        assertThat(Files.exists(documentoVerso)).isFalse();
        verify(agendamentoRepository).delete(agendamento);

        ArgumentCaptor<AuditoriaLog> auditoriaCaptor = ArgumentCaptor.forClass(AuditoriaLog.class);
        verify(auditoriaLogRepository).save(auditoriaCaptor.capture());

        AuditoriaLog auditoriaLog = auditoriaCaptor.getValue();
        assertThat(auditoriaLog.getAcao()).isEqualTo("AGENDAMENTO_REMOVIDO_POR_EXPIRACAO");
        assertThat(auditoriaLog.getAgendamentoId()).isEqualTo(1L);
        assertThat(auditoriaLog.getAcao()).doesNotContain("Maria", "11122233344", "@");
    }

    @Test
    void deveNaoFazerNadaQuandoNaoExistiremAgendamentosExpirados() {
        // given
        LocalDate hoje = LocalDate.of(2026, 3, 21);

        when(agendamentoRepository.buscarAgendamentosAntesData(hoje)).thenReturn(List.of());

        // when
        limpezaAgendamentoService.removerAgendamentosExpirados(hoje);

        // then
        verify(agendamentoRepository, never()).delete(any(Agendamento.class));
        verify(auditoriaLogRepository, never()).save(any(AuditoriaLog.class));
    }

    @Test
    void deveManterAgendamentoQuandoFalharExclusaoDeArquivo() throws IOException {
        // given
        LocalDate hoje = LocalDate.of(2026, 3, 21);
        Path diretorioNaoVazio = Files.createDirectory(diretorioTemporario.resolve("documento"));
        Files.createFile(diretorioNaoVazio.resolve("interno.txt"));
        Agendamento agendamento = criarAgendamento(2L, diretorioNaoVazio, null);

        when(agendamentoRepository.buscarAgendamentosAntesData(hoje)).thenReturn(List.of(agendamento));

        // when
        limpezaAgendamentoService.removerAgendamentosExpirados(hoje);

        // then
        assertThat(Files.exists(diretorioNaoVazio)).isTrue();
        verify(agendamentoRepository, never()).delete(any(Agendamento.class));
        verify(auditoriaLogRepository, never()).save(any(AuditoriaLog.class));
    }

    @Test
    void deveDelegarExecucaoAgendadaParaBuscaDeExpirados() throws NoSuchMethodException {
        // given
        when(agendamentoRepository.buscarAgendamentosAntesData(any(LocalDate.class))).thenReturn(List.of());

        // when
        limpezaAgendamentoService.executarLimpezaAgendada();

        // then
        verify(agendamentoRepository).buscarAgendamentosAntesData(any(LocalDate.class));

        Method metodoAgendado = LimpezaAgendamentoService.class.getMethod("executarLimpezaAgendada");
        Scheduled scheduled = metodoAgendado.getAnnotation(Scheduled.class);

        assertThat(scheduled).isNotNull();
        assertThat(scheduled.cron()).isEqualTo("0 5 0 * * *");
    }

    private Agendamento criarAgendamento(Long id, Path documentoFrente, Path documentoVerso) {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(id);
        agendamento.setNomeRequerente("Maria");
        agendamento.setCpfCnpj("11122233344");
        agendamento.setEmail("maria@email.com");
        agendamento.setDocumentoFrentePath(documentoFrente != null ? documentoFrente.toString() : null);
        agendamento.setDocumentoVersoPath(documentoVerso != null ? documentoVerso.toString() : null);
        return agendamento;
    }
}
