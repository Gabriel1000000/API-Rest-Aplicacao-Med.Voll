package med.voll.api.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.consulta.agendamento.DadosAgendamentoConsulta;
import med.voll.api.repository.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorDeAgendamentoDeConsulta {
    
    @Autowired
    private PacienteRepository repository;
    
    public void validar(DadosAgendamentoConsulta dados){
        var pacienteEstaAivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAivo) {
            throw new ValidacaoException("Nao pode agendar consultas para pacientes excluidos!");
        }
    }
}
