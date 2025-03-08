package med.voll.api.medico;

import jakarta.validation.Valid;
import med.voll.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico( 
    @NotNull
    Long id,
    String nome, 
    String telefone,
    @Valid DadosEndereco endereco) {
    
}
