package med.voll.api.controller;

import lombok.var;
// import java.util.List;
import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import med.voll.api.medico.DadosListaMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.data.domain.Pageable;
import med.voll.api.medico.DadosAtualizacaoMedico;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
        return repository.findAllByAtivoTrue(paginacao).map(DadosListaMedico::new);
    }
    // para trazer uma lista
    // public List<DadosListaMedico> listar(){
    //     return repository.findAll().stream().map(DadosListaMedico::new).toList();
    // }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarMeidco(dados);
        
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
