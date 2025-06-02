package med.voll.api.repository;


import med.voll.api.paciente.Paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
    
}
