package med.voll.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/hello")
public class Controller {
    @GetMapping
    public String OlaMundo() {
        return "Hello World";
    }
    
}
