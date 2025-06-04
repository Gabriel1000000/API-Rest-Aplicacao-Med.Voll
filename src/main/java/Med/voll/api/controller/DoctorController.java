package med.voll.api.controller;

import lombok.var;
// import java.util.List;
import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import med.voll.api.medico.DadosListaMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosDetalhesMedicos;
import med.voll.api.repository.MedicoRepository;
import org.springframework.data.domain.Pageable;
import med.voll.api.medico.DadosAtualizacaoMedico;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/medico")
public class DoctorController {
    
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhesMedicos(medico));
    }

    @GetMapping
    // para trazer uma lista com paginaçõa.
    public ResponseEntity <Page<DadosListaMedico>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListaMedico::new);
        return ResponseEntity.ok(page);
    }
    // para trazer uma lista
    // public List<DadosListaMedico> listar(){
    //     return repository.findAll().stream().map(DadosListaMedico::new).toList();
    // }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarMeidco(dados);
        return ResponseEntity.ok(new DadosDetalhesMedicos(medico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhesMedicos(medico));
    }
}
