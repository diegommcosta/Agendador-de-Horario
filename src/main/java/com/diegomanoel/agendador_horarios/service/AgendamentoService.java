package com.diegomanoel.agendador_horarios.service;

import com.diegomanoel.agendador_horarios.infrastructure.entity.Agendamento;
import com.diegomanoel.agendador_horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    // Injeta o repository de agendamento
    private final AgendamentoRepository agendamentoRepository;

    // Post
    public Agendamento salvarAgendamento(Agendamento agendamento) {

        LocalDateTime dataHoraAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime dataHoraFim = dataHoraAgendamento.plusHours(1);

        Agendamento agendados = agendamentoRepository
                .findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), dataHoraAgendamento, dataHoraFim);

        if (Objects.nonNull(agendados)) {
            throw new RuntimeException("Horário já está preenchido.");
        }
        ;

        return agendamentoRepository.save(agendamento);
    };

    // Delete
    public void deletarAgendamento(LocalDateTime horaDataAgendamento, String cliente) {
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(horaDataAgendamento, cliente);
    };

    // Get
    public Agendamento buscarAgendamentosDia(LocalDate data) {
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime ultimaHoraDia = data.atTime(23, 59, 59);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, ultimaHoraDia);
    };

    // Update
    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
        Agendamento agenda = agendamentoRepository.findByClienteAndDataHoraAgendamento(cliente, dataHoraAgendamento);

        if (Objects.isNull(agenda)) {
            throw new RuntimeException("Não hà agendamento para este horário");
        }

        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    };
}
