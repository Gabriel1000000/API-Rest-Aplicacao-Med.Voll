package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.stereotype.Component;

import med.voll.api.consulta.agendamento.DadosAgendamentoConsulta;

@Component
public interface ValidadorDeAgendamentoDeConsulta {
    
    void validar(DadosAgendamentoConsulta dados);
    
}
