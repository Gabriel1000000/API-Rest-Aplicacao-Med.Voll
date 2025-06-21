package med.voll.api.consulta.validacoes.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.consulta.agendamento.DadosAgendamentoConsulta;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHoraAntecipada implements ValidadorDeAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinuto = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinuto < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecendencia de no minimo 30 minutos!");
        }
    }
}
