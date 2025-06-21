package med.voll.api.consulta.cancelamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(

    @NotNull
    Long idConsulta,

    @NotNull
    MotivoCancelamento motivo

) {
    
}
