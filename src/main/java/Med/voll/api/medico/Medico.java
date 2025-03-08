package med.voll.api.medico;

import lombok.Getter;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import med.voll.api.endereco.Endereco;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicos")
@Entity(name = "Medico")
@EqualsAndHashCode(of = "id")
public class Medico {

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome=dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade=dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @Column(nullable = false)
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    
    private Boolean ativo;


    public void atualizarMeidco(DadosAtualizacaoMedico dados) {
        if (dados.nome()!= null) {
            
            this.nome=dados.nome();
        }
        if (dados.telefone()!=null) {
            this.telefone=dados.telefone();
        }
        if (dados.endereco()!=null) {
            this.endereco.atualizarEndereco(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
