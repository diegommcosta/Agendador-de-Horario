package com.diegomanoel.agendador_horarios.controller;

import com.diegomanoel.agendador_horarios.infrastructure.entity.Agendamento;
import com.diegomanoel.agendador_horarios.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgendamentoController {
    // Injeta o Service
    private final AgendamentoService agendamentoService;

    // Mapeia a rota do post
    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento){
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamento));
    }

}
