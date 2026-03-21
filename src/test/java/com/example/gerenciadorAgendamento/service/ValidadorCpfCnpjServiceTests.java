package com.example.gerenciadorAgendamento.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidadorCpfCnpjServiceTests {

    private final ValidadorCpfCnpjService validadorCpfCnpjService = new ValidadorCpfCnpjService();

    @Test
    void deveAceitarCpf() {
        // given
        String cpf = "12345678909";

        // when
        boolean resultado = validadorCpfCnpjService.ehValido(cpf);

        // then
        assertThat(resultado).isTrue();
    }

    @Test
    void deveRejeitarCpf() {
        // given
        String cpf = "12345678900";

        // when
        boolean resultado = validadorCpfCnpjService.ehValido(cpf);

        // then
        assertThat(resultado).isFalse();
    }
}
