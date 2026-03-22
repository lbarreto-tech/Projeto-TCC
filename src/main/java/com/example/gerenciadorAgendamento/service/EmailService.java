package com.example.gerenciadorAgendamento.service;

import com.example.gerenciadorAgendamento.model.EmailRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Async
    public void enviarEmail(EmailRequest request) {
        try {
            String mensagem = gerarMensagem(request);

            logger.info("Enviando email...");
            logger.info("\n{}", mensagem);

        } catch (Exception e) {
            logger.error("Erro ao enviar email, tentando novamente...", e);
            retry(request);
        }
    }

    private void retry(EmailRequest request) {
        try {
            Thread.sleep(2000);
            enviarEmail(request);
        } catch (InterruptedException e) {
            logger.error("Erro no reenvio do email", e);
        }
    }

    private String gerarMensagem(EmailRequest request) {
        return "Olá " + request.getNomeRequerente() + ",\n" +
                "Seu evento foi " + request.getTipo() + ".\n" +
                "Responsável: " + request.getNomeResponsavel() + "\n" +
                "CPF/CNPJ: " + request.getCpfCnpj() + "\n" +
                "Data do evento: " + request.getDataEvento();
    }
}
