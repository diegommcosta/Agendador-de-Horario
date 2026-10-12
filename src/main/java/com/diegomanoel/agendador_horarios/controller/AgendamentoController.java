package com.diegomanoel.agendador_horarios.controller;

import com.diegomanoel.agendador_horarios.infrastructure.entity.Agendamento;
import com.diegomanoel.agendador_horarios.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {
    // Injeta o Service
    private final AgendamentoService agendamentoService;

    // Mapeia o agendamento
    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento){
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamento));
    }

    // Mapeia o delete do agendamento
    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestBody LocalDateTime dataHoraAgendamento, String cliente){
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

    // Mapeia o get
    @GetMapping
    public ResponseEntity<List<Agendamento>> buscarAgendamentosDia(LocalDate data){
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDia(data));
    }

    // Mapeia o update
    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamentos(@RequestBody Agendamento agendamento, LocalDateTime dataHoraAgendamento, String cliente){
        return ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamento, cliente, dataHoraAgendamento));
    }
}
