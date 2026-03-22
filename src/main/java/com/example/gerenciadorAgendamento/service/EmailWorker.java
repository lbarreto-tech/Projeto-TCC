package com.example.gerenciadorAgendamento.service;

import com.example.gerenciadorAgendamento.model.EmailRequest;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class EmailWorker {

    private final Queue<EmailRequest> fila = new LinkedList<>();
    private final EmailService emailService;

    public EmailWorker(EmailService emailService) {
        this.emailService = emailService;
    }

    public void adicionarNaFila(EmailRequest request) {
        fila.add(request);
        processarFila();
    }

    private void processarFila() {
        while (!fila.isEmpty()) {
            EmailRequest request = fila.poll();
            emailService.enviarEmail(request);
        }
    }
}