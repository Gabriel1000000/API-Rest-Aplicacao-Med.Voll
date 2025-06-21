package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.consulta.agendamento.AgendarConsultas;
import med.voll.api.consulta.cancelamento.DadosCancelamentoConsulta;

@RestController
@RequestMapping("/cancelamento")
@Tag(name = "Cancelamento de consulta", description = "Cancelar consultas agendadas")
@SecurityRequirement(name = "bearer-key")
public class CancelamentoController {
    
    @Autowired
    private AgendarConsultas agenda;

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        var dto = agenda.cancelar(dados);
        return ResponseEntity.ok(dto);
    }
}
