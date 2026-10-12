package com.diegomanoel.agendador_horarios.infrastructure.repository;

import com.diegomanoel.agendador_horarios.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Query para Post
    Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim);

    // Query para Delete
    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

    // Query para Get
    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);

    // Query para Update
    Agendamento findByClienteAndDataHoraAgendamento(String cliente, LocalDateTime dataHoraAgendamento);
}
