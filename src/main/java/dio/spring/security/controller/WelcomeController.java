package dio.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping //primeira metodo get direncionado para pagina inicial
    public String welcome(){
        return "Welcome to My Spring Boot Web API";
    }
    @GetMapping("/users") // rota de usuário só imprimir essa rota foi autorizada para perfil usuário acessar
    @PreAuthorize("hasAnyRole('MANAGERS', 'USERS')") //quais os perfis cada rota permintir sua visualização
    public String users(){
        return "Authorized user";
    }
    @GetMapping("/managers")
   @PreAuthorize("hasRole('MANAGERS')")
    public String managers(){
        return "Authorized manager";

    }

}
