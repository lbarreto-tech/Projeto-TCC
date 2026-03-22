package com.example.gerenciadorAgendamento.service;

import com.example.gerenciadorAgendamento.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ChecarConflitoService {

    private final AgendamentoRepository repository;

    public ChecarConflitoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public boolean checarConflito(LocalDate dataEvento) {
        boolean existe = repository.existeAgendamentoAtivoNaData(dataEvento);
        return !existe;
    }
}
