package med.voll.api.infra.springDoc;

import java.util.List;


import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class CustomOpenApiConfig {

    @Bean
    public OpenApiCustomizer SwaggerTagOrderConfig () {
        return openApi -> {
            // Ordenação manual das TAGS (Seções no Swagger)
            openApi.setTags(List.of(
                    new Tag().name("Login").description("Autenticação e geração de token JWT"),
                    new Tag().name("Médicos").description("CRUD de médicos"),
                    new Tag().name("Pacientes").description("CRUD de pacientes"),
                    new Tag().name("Agendamento de consulta").description("Agendar novas consultas"),
                    new Tag().name("Cancelamento de consulta").description("Cancelar consultas agendadas")
            ));
        };
    }
}
