package med.voll.api.endereco;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor

public class Endereco {

    public Endereco(DadosEndereco endereco) {
        //TODO Auto-generated constructor stub
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.cidade = endereco.cidade();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();

    }
    
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
}
