package com.example.gerenciadorAgendamento.service;

import com.example.gerenciadorAgendamento.dto.AgendamentoDTO;
import com.example.gerenciadorAgendamento.dto.ResponseSolicitacaoDTO;
import com.example.gerenciadorAgendamento.model.Agendamento;
import com.example.gerenciadorAgendamento.model.AgendamentoStatus;
import com.example.gerenciadorAgendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SolicitacoesService {
    @Autowired
    public AgendamentoRepository agendamentoRepository;

    public ResponseSolicitacaoDTO cadastrarSolicitacao(AgendamentoDTO agendamentoDTO){
        validarCPF(agendamentoDTO.getCpfCnpj());
        validarDiaSemana(agendamentoDTO.getDataEvento());
        validarConflito(agendamentoDTO.getDataEvento());

        Agendamento agendamento = new Agendamento();
        agendamento.setDataEvento(agendamentoDTO.getDataEvento());
        agendamento.setCpfCnpj(agendamentoDTO.getCpfCnpj());
        agendamento.setStatus(AgendamentoStatus.PENDENTE);
        agendamento.setCriadoEm(LocalDateTime.now());

        agendamentoRepository.save(agendamento);
        return new ResponseSolicitacaoDTO(agendamentoDTO.getId(), "Solicitação criada com sucesso!");
    }

    private void validarCPF(String doc){
        if(doc.matches("\\\\d{11}|\\\\d{14}")){
            throw new RuntimeException("CPF/CNPJ Inválido.");
        }
    }

    private void validarDiaSemana(LocalDate data){
        if(data.getDayOfWeek() == DayOfWeek.SATURDAY || data.getDayOfWeek() == DayOfWeek.SUNDAY){
            throw new RuntimeException("Não é permitido agendar em fim de semana.");
        }
    }

    private void validarConflito(LocalDate data){
        boolean indisponivel = agendamentoRepository.existeAgendamentoAtivoNaData(data);
        if (indisponivel){
            throw new RuntimeException("Já existe solicitação para esta data");
        }
    }

}
