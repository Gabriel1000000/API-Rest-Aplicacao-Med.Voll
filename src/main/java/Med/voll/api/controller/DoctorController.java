package med.voll.api.controller;
import med.voll.api.medico.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;







@RestController
@RequestMapping("/registration-doctor")
public class DoctorController {
    
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico data){
        System.out.println(data);
    }

    // public Void cadastrar(@RequestBody String json) {
    //     System.out.println(json);
    // }
}
