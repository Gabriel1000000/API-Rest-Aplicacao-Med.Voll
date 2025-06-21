package med.voll.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.consulta.agendamento.AgendarConsultas;
import med.voll.api.consulta.agendamento.DadosAgendamentoConsulta;
import med.voll.api.consulta.agendamento.DadosDetalhamentoConsulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/agendamento")
@Tag(name = "Agendamento de consulta", description = "Agendamento de consultas médicas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    
    @Autowired
    private AgendarConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }
}
