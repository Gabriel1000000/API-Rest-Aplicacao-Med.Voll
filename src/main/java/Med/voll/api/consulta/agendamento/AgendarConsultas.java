package med.voll.api.consulta.agendamento;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import med.voll.api.consulta.Consulta;
import med.voll.api.consulta.cancelamento.DadosCancelamentoConsulta;
import med.voll.api.consulta.cancelamento.DadosDetalhamentoCancelamento;
import med.voll.api.consulta.validacoes.agendamento.ValidacaoException;
import med.voll.api.consulta.validacoes.agendamento.ValidadorDeAgendamentoDeConsulta;
import med.voll.api.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class AgendarConsultas {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");
        }

        var medicos = medicoRepository.escolherMedicosDisponiveisNaData(
            dados.especialidade(),
            dados.data(),
            PageRequest.of(0, 1)
        );

        if (medicos.isEmpty()) {
            throw new ValidacaoException("Nenhum médico disponível nessa data!");
        }

        return medicos.getContent().get(0);
    }

    // public DadosDetalhamentoCancelamento cancelar(DadosCancelamentoConsulta dados) {

    //     if (!consultaRepository.existsById(dados.idConsulta())) {
    //         throw new ValidacaoException("Id da consulta informado não existe!");
    //     }

    //     var consulta = consultaRepository.getReferenceById(dados.idConsulta());
    //     consulta.cancelar(dados.motivo());

    //      return new DadosDetalhamentoCancelamento(consulta.getId(), dados.motivo(), "Consulta cancelada com sucesso!");

    // }

    public DadosDetalhamentoCancelamento cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
        
        return new DadosDetalhamentoCancelamento(consulta.getId(), dados.motivo(), "Consulta cancelada com sucesso!");
    }   

}
