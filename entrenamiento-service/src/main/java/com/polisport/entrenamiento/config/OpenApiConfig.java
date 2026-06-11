package com.polisport.entrenamiento.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("PoliSport — Gestión de Entrenamientos API")
                .description("Documentación del microservicio de Gestión de Entrenamientos del sistema PoliSport")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Equipo PoliSport")
                    .email("contacto@polisport.cl")
                    .url("https://github.com/tu-repo/polisport"))
                .license(new License()
                    .name("MIT")
                    .url("https://opensource.org/licenses/MIT")));
    }
}
