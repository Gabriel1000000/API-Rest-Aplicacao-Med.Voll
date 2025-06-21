package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;


import med.voll.api.consulta.Consulta;
import med.voll.api.consulta.cancelamento.MotivoCancelamento;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {


    Boolean existsByPacienteIdAndDataBetweenAndMotivoCancelamento(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario, MotivoCancelamento motivo);


    // Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

    
}
