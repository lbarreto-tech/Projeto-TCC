package com.example.gerenciadorAgendamento.controller;

import com.example.gerenciadorAgendamento.model.Agendamento;
import com.example.gerenciadorAgendamento.model.AgendamentoStatus;
import com.example.gerenciadorAgendamento.repository.AgendamentoRepository;
import com.example.gerenciadorAgendamento.service.ValidarDiaService;
import com.example.gerenciadorAgendamento.service.ChecarConflitoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoController {

    private final ValidarDiaService validarDiaService;
    private final ChecarConflitoService conflitoService;
    private final AgendamentoRepository repository;

    public SolicitacaoController(
            ValidarDiaService validarDiaService,
            ChecarConflitoService conflitoService,
            AgendamentoRepository repository) {

        this.validarDiaService = validarDiaService;
        this.conflitoService = conflitoService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> criarSolicitacao(@RequestBody Map<String, String> payload) {

        try {
            if (payload.get("data_evento") == null || payload.get("data_evento").isBlank() ||
                    payload.get("nome_requerente") == null || payload.get("nome_requerente").isBlank() ||
                    payload.get("cpf_cnpj") == null || payload.get("cpf_cnpj").isBlank() ||
                    payload.get("email") == null || payload.get("email").isBlank()) {

                return ResponseEntity.badRequest().body(
                        Map.of("code", "INVALID_PAYLOAD", "message", "Campo obrigatório ausente")
                );
            }

            LocalDate dataEvento = LocalDate.parse(payload.get("data_evento"));

            if (!validarDiaService.validarDia(dataEvento)) {
                return ResponseEntity.badRequest().body(
                        Map.of("code", "INVALID_DAY", "message", "Datas permitidas: sábado ou domingo")
                );
            }

            if (!conflitoService.checarConflito(dataEvento)) {
                return ResponseEntity.status(409).body(
                        Map.of("code", "DATE_CONFLICT", "message", "Já existe uma solicitação para esta data")
                );
            }

            Agendamento agendamento = new Agendamento();

            agendamento.setDataEvento(dataEvento);
            agendamento.setNomeRequerente(payload.get("nome_requerente"));
            agendamento.setCpfCnpj(payload.get("cpf_cnpj"));
            agendamento.setEmail(payload.get("email"));
            agendamento.setHorario(payload.getOrDefault("hora_inicio", "00:00"));
            agendamento.setNomeResponsavel(payload.getOrDefault("nome_responsavel", "N/A"));
            agendamento.setEndereco(payload.getOrDefault("endereco", "N/A"));
            agendamento.setBairro(payload.getOrDefault("bairro", "N/A"));
            agendamento.setTelefone(payload.getOrDefault("telefone", "N/A"));

            agendamento.setPublicoEstimado(
                    payload.get("publico_estimado") != null
                            ? Integer.parseInt(payload.get("publico_estimado"))
                            : 0
            );

            agendamento.setDescricaoEvento(
                    payload.getOrDefault("descricao", "Sem descrição")
            );

            agendamento.setStatus(AgendamentoStatus.PENDENTE);

            repository.save(agendamento);

            return ResponseEntity.status(201).body(
                    Map.of(
                            "id", UUID.randomUUID().toString(),
                            "message", "Solicitação recebida"
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("code", "INVALID_PAYLOAD", "message", "Erro ao processar requisição")
            );
        }
    }
}
