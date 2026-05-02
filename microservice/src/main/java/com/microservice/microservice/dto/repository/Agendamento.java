package com.microservice.microservice.dto.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//Get and Setters e construtores
//Entidade no bancco de dados uma tabela
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agendamento")
public class Agendamento {

    //transportar dados para o banco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    //O servico prestado
    private String servicoPrestado;

    private String nomeFuncionario;

    //Data futura
    private LocalDateTime dataHoraAgendamento;

    private String cliente;

    //Sempre que for agendada a data do servico
    private LocalDateTime dataAgendamento = LocalDateTime.now();

    private String telefoneCliente;



}
