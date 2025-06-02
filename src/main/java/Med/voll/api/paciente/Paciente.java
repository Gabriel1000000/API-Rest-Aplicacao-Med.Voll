package med.voll.api.paciente;

import lombok.Getter;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Embedded;
import med.voll.api.endereco.Endereco;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
@Entity(name = "Paciente")
@EqualsAndHashCode(of = "id")
public class Paciente {

    public Paciente(DadosCadastroPaciente dados){
        this.ativo=true;
        this.nome=dados.nome();
        this.cpf=dados.cpf();
        this.email=dados.email();
        this.telefone=dados.telefone();
        this.endereco= new Endereco(dados.endereco());
    }
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @Column(nullable = false)
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    
    private Boolean ativo;

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
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
    public void inativar() {
        this.ativo=false;
    }
    
}
