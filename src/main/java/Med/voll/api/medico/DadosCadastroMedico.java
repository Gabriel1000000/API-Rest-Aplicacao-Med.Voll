package med.voll.api.medico;

import jakarta.validation.Valid;
import med.voll.api.endereco.DadosEndereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMedico(
    @NotBlank
    String nome, 
    @NotBlank
    @Email
    String email, 
    @NotBlank
    String telefone,
    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm, 

    @NotNull
    Especialidade especialidade, 

    @NotNull @Valid DadosEndereco endereco) {
    
}