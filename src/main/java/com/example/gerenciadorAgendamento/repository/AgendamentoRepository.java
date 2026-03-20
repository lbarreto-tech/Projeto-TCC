package com.example.gerenciadorAgendamento.repository;

import com.example.gerenciadorAgendamento.model.Agendamento;
import com.example.gerenciadorAgendamento.model.AgendamentoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    /**
     * Verifica se existe algum agendamento na data que não está rejeitado.
     * Datas com PENDENTE ou APROVADO estão ocupadas.
     */
    @Query("SELECT COUNT(a) > 0 FROM Agendamento a " +
            "WHERE a.dataEvento = :data AND a.status <> 'REJEITADO'")
    boolean existeAgendamentoAtivoNaData(@Param("data") LocalDate data);

    /**
     * Busca todos os agendamentos por status.
     */
    List<Agendamento> findByStatusOrderByDataEventoAsc(AgendamentoStatus status);

    /**
     * Retorna todas as datas que estão ocupadas (PENDENTE ou APROVADO).
     */
    @Query("SELECT a.dataEvento FROM Agendamento a WHERE a.status <> 'REJEITADO'")
    List<LocalDate> buscarTodasDatasOcupadas();

    /**
     * Busca agendamentos cuja data do evento já passou, para limpeza automática.
     */
    @Query("SELECT a FROM Agendamento a WHERE a.dataEvento < :hoje")
    List<Agendamento> buscarAgendamentosAntesData(@Param("hoje") LocalDate hoje);

    /**
     * Verifica conflito de data excluindo um agendamento específico (útil para edição).
     */
    @Query("SELECT COUNT(a) > 0 FROM Agendamento a " +
            "WHERE a.dataEvento = :data AND a.status <> 'REJEITADO' AND a.id <> :id")
    boolean existeAgendamentoAtivoNaDataExcluindoId(@Param("data") LocalDate data, @Param("id") Long id);

    Optional<Agendamento> findByIdAndStatus(Long id, AgendamentoStatus status);

}
