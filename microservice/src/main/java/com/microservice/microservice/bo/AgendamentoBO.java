package com.microservice.microservice.bo;

import com.microservice.microservice.dao.AgendamentoDAO;
import com.microservice.microservice.dto.repository.Agendamento;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor//Injeção de dependencia para salvar algo exemplo dao
public class AgendamentoBO {
    //Chamar a classe Agendamento DAO dentro do BO para salvar um agendamento
    //Salvando agendamento
    private final AgendamentoDAO agendamentoDAO;

    //Marcação de servico com cliente , regras de negocio
    //Reservamento
    //Agendamento a cada hora
    //Para não permitir que os horários tenha conflito com outros
    //Retornar um agendamento , receber um agendamento
    public Agendamento salvarAgendamento (Agendamento agendamento){
        //Pegar o horario de agendamento hora
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        //Verificação se não tem agendamento dentro de uma hora
        //Exemplo agendamentos de uma em uma hora
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusMinutes(1);
        //Me da o Servico , hora fim e hora inicio
        Agendamento servicosAgendados = agendamentoDAO.findByServicoPrestadoAndDataHoraAgendamentoBetween(agendamento.getServicoPrestado(),horaAgendamento,horaFim);


        //Se servico for null entao salva  para o periodo
        /*if(Objects.isNull(servicosAgendados)){
           return agendamentoDAO.save(servicosAgendados);
        }else {
            throw new RuntimeException("Não há vagas para esse horário");
        }*/

        //Se nonNull - se servicos ja existe retorno uma exception
        if(Objects.nonNull(servicosAgendados)){
            throw new RuntimeException("Não há vagas disponiveis");
        }return agendamentoDAO.save(servicosAgendados);//Salva agendamento


    }
    //Delete - Metodo
    //Pode ser pelo cliente , data hora agendamento passar string cliente ou data hora
    public void deletarAgendamento(LocalDateTime dataHoraAgendamento , String cliente){
        agendamentoDAO.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

    }

    //Buscar so por data e com data posso buscar a hora
    public List<Agendamento> buscarAgendamentosDia(LocalDate data){
        LocalDateTime buscarInicioDoDia = data.atStartOfDay();
        LocalDateTime buscarFimDoDia = data.atTime(23,59, 59);

        return agendamentoDAO.findByDataHoraAgendamentoBetween(buscarInicioDoDia,buscarFimDoDia);

    }

    //Editar buscar uma nova data e hora agendamento
    //Buscar cliente e data e hora que ele setou
    public Agendamento editarAgendamento(Agendamento agendamento ,String cliente , LocalDateTime dataHoraAgendamento){
        Agendamento agenda = agendamentoDAO.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento , cliente);
        //Caso não tenha o horario agendado
        if(Objects.isNull(agenda)){
            throw new RuntimeException("Horário disponivel , marque seu horario conosco");

        }
        //Setando com informações novas
        agendamento.setId(agenda.getId());
        return agendamentoDAO.save(agendamento);


    }
}
