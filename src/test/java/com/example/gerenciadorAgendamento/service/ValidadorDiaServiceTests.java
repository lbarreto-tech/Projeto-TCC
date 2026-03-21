package com.example.gerenciadorAgendamento.service;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidadorDiaServiceTests {

    private final ValidadorDiaService validadorDiaService = new ValidadorDiaService();

    @Test
    void deveAceitarSabado() {
        // given
        LocalDate dataEvento = LocalDate.of(2026, 5, 9);

        // when
        boolean resultado = validadorDiaService.ehValido(dataEvento);

        // then
        assertThat(resultado).isTrue();
    }

    @Test
    void deveAceitarDomingo() {
        // given
        LocalDate dataEvento = LocalDate.of(2026, 5, 10);

        // when
        boolean resultado = validadorDiaService.ehValido(dataEvento);

        // then
        assertThat(resultado).isTrue();
    }

    @Test
    void deveRejeitarSegunda() {
        // given
        LocalDate dataEvento = LocalDate.of(2026, 5, 11);

        // when
        boolean resultado = validadorDiaService.ehValido(dataEvento);

        // then
        assertThat(resultado).isFalse();
    }
}
