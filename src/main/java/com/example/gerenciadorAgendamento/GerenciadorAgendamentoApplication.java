package com.example.gerenciadorAgendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GerenciadorAgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorAgendamentoApplication.class, args);
	}

}
