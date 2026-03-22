package com.example.gerenciadorAgendamento.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class ValidarDiaService {

    public boolean validarDia(LocalDate dataEvento) {
        DayOfWeek dia = dataEvento.getDayOfWeek();

        // Verificação Sábado ou Domingo
        if (dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) {
            return false;
        }

        return true;
    }
}
