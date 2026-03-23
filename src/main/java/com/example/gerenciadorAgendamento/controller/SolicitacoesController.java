package com.example.gerenciadorAgendamento.controller;

import com.example.gerenciadorAgendamento.dto.AgendamentoDTO;
import com.example.gerenciadorAgendamento.dto.ResponseSolicitacaoDTO;
import com.example.gerenciadorAgendamento.service.SolicitacoesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/solicitacoes")
public class SolicitacoesController {

    private SolicitacoesService solicitacoesService;

    @PostMapping
    public ResponseEntity<ResponseSolicitacaoDTO> cadastrarSolicitacao(@RequestBody(required = true)AgendamentoDTO agendamentoDTO){
        try {
            ResponseSolicitacaoDTO resultado = this.solicitacoesService.cadastrarSolicitacao(agendamentoDTO);
            return ResponseEntity.ok(resultado);
        }catch (Exception e){
            ResponseSolicitacaoDTO erro = new ResponseSolicitacaoDTO(null, e.getMessage());
            return ResponseEntity.status(400).body(erro);
        }
    }
}
