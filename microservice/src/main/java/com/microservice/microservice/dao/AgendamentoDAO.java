package com.microservice.microservice.dao;

import com.microservice.microservice.dto.repository.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


//Extende o JPA | id e classe
//Aqui vamos configurar comportamento do banco de dados
//Acesso e manipulação do banco
public interface AgendamentoDAO extends JpaRepository<Agendamento,Long> {
    //FIND com BETWEEN - Consulta entre os horários
    //Serviço, data hora ,agendamento
    Agendamento findByServicoPrestadoAndDataHoraAgendamentoBetween(String servicoPrestado , LocalDateTime dataHoraInicio , LocalDateTime dataHoraFim);


    //DELETE
    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

    //Todos que agendaram determindado dia
    //Data e hora inicial e fim
    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);

    //Edição data e hora do agendamento
    //Find busco  o novo e antigo que quero alterar
    Agendamento findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento,String cliente );

}
