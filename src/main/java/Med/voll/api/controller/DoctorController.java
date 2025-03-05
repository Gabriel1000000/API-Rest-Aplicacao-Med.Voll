package med.voll.api.controller;

import jakarta.validation.Valid;
// import java.util.List;
import med.voll.api.medico.Medico;
import jakarta.transaction.Transactional;
import med.voll.api.medico.DadosListaMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/medico")
public class DoctorController {
    
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    // para trazer uma lista com paginaçõa.
    public Page<DadosListaMedico> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListaMedico::new);
    }
    // para trazer uma lista
    // public List<DadosListaMedico> listar(){
    //     return repository.findAll().stream().map(DadosListaMedico::new).toList();
    // }
}
