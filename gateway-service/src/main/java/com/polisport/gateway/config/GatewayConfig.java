package com.polisport.gateway.config;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("atletas", r -> r.path("/api/atletas/**")
                        .uri("http://atletas-service:8081"))
                .route("biometria", r -> r.path("/api/v1/biometria/**")
                        .uri("http://biometria-service:8082"))
                .route("competencias", r -> r.path("/api/competencias/**")
                        .uri("http://competencia-service:8083"))
                .route("contratos", r -> r.path("/api/contratos/**")
                        .uri("http://contratos-service:8084"))
                .route("entrenamientos", r -> r.path("/api/entrenamientos/**")
                        .uri("http://entrenamiento-service:8085"))
                .route("inventario", r -> r.path("/api/v1/inventario/**")
                        .uri("http://inventario-service:8086"))
                .route("instalaciones", r -> r.path("/api/v1/instalaciones/**")
                        .uri("http://inventario-service:8086"))
                .route("salud", r -> r.path("/api/v1/salud/**")
                        .uri("http://salud-service:8087"))
                .route("iam", r -> r.path("/api/v1/iam/**")
                        .uri("http://iam-service:8088"))
                .route("nutricion", r -> r.path("/api/v1/nutricion/**")
                        .uri("http://nutricion-service:8089"))
                .route("staff", r -> r.path("/api/v1/staff/**")
                        .uri("http://staff-service:8090"))
                .build();
    }
}