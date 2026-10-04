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

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento){

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = horaAgendamento.plusHours(1);

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), horaAgendamento, horaFim);

        if(Objects.nonNull(agendados)){
            throw new RuntimeException("Horário já está preenchido.");
        };
        return agendamentoRepository.save(agendamento);
    };

    public void deletarAgendamento(LocalDateTime horaDataAgendamento, String cliente){
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(horaDataAgendamento, cliente);
    };

    public Agendamento buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime ultimaHoraDia = data.atTime(23, 59, 59);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, ultimaHoraDia);
    }
}
