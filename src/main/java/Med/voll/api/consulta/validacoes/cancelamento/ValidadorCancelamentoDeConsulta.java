package med.voll.api.consulta.validacoes.cancelamento;

import med.voll.api.consulta.cancelamento.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
