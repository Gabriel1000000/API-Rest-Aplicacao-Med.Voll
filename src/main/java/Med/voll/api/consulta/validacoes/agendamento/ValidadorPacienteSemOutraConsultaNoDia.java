package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.agendamento.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorDeAgendamentoDeConsulta {
    
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var priemeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetweenAndMotivoCancelamento(dados.idPaciente(), priemeiroHorario, ultimoHorario, dados.motivo());
        
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui consulta agendada para esse dia!");
            
        }

    }
}
