package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

// import med.voll.api.endereco.Endereco;

public record DadosDetalhesMedicos(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {

        public DadosDetalhesMedicos(Medico medico){
            this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
        }

}
