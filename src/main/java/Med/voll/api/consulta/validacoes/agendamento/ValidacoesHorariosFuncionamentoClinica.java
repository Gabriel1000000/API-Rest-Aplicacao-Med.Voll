package med.voll.api.consulta.validacoes.agendamento;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.consulta.agendamento.DadosAgendamentoConsulta;

@Component
public class ValidacoesHorariosFuncionamentoClinica implements ValidadorDeAgendamentoDeConsulta {
    
    
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour()<7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour()>18;
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica!");
        }
    }
}
