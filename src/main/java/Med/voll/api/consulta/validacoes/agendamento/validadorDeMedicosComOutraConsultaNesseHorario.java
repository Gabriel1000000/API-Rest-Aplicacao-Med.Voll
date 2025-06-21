package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.agendamento.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;

@Component
public class validadorDeMedicosComOutraConsultaNesseHorario implements ValidadorDeAgendamentoDeConsulta {
    
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Medico ja possui consulta agendada nesse horario!");
        }
    }


}
