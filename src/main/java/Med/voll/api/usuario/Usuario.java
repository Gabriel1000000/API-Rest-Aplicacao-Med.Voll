package med.voll.api.usuario;


import lombok.Getter;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
// import jakarta.persistence.Column;
// import jakarta.persistence.Embedded;
// import jakarta.persistence.EnumType;
// import med.voll.api.endereco.Endereco;
// import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
@Entity(name = "Usuario")
@EqualsAndHashCode(of = "id")
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
}
