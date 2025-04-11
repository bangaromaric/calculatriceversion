package ga.banga.calculatriceversion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI calculatriceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Calculatrice")
                        .description("API REST pour effectuer des opérations mathématiques de base")
                        .version("0.1.0")
                        .contact(new Contact()
                                .name("Équipe de développement")
                                .email("team@example.ga")));
    }
}
