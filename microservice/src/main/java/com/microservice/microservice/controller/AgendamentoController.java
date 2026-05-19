package com.microservice.microservice.controller;


import com.microservice.microservice.bo.AgendamentoBO;
import com.microservice.microservice.dto.repository.Agendamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AgendamentoController {

    //Instancia a regra de negocio
    private final AgendamentoBO agendamentoBO;
    //Aqui quem  vai salvar retorno do save
    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendameto(@RequestBody Agendamento agendamento){
        return ResponseEntity.accepted().body(agendamentoBO.salvarAgendamento(agendamento));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente,
                                                   @RequestParam LocalDateTime dataHoraAgendamento){


        agendamentoBO.deletarAgendamento(dataHoraAgendamento,cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Agendamento> buscarAgendamentosDia(@RequestParam LocalDate data){
        ResponseEntity<Agendamento> body = ResponseEntity.ok().body(agendamentoBO.buscarAgendamentosDia(data));
        return body;

    }
    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamentos(@RequestBody Agendamento agendamento,
                                                           @RequestParam String cliente,
                                                           @RequestParam LocalDateTime dataHoraAgendamento) {
        return ResponseEntity.accepted().body(agendamentoBO.editarAgendamento(agendamento,
                cliente, dataHoraAgendamento));
    }


}
