package com.polisport.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion central del API Gateway (Spring Cloud Gateway).
 *
 * <p>Este gateway es el unico punto de entrada publico de la plataforma
 * PoliSport: recibe todas las peticiones externas en el puerto {@code 8080}
 * y las enruta internamente hacia el microservicio correspondiente segun el
 * prefijo de la URL solicitada. Cada microservicio de negocio (atletas,
 * biometria, competencias, contratos, entrenamientos, inventario, salud,
 * iam, nutricion y staff) queda expuesto detras de este gateway, por lo que
 * los clientes nunca necesitan conocer el host/puerto real de cada servicio.</p>
 *
 * <p>Ventajas de este patron para la arquitectura de microservicios:</p>
 * <ul>
 *     <li>Desacopla a los clientes de la topologia interna de servicios.</li>
 *     <li>Permite agregar en un solo lugar preocupaciones transversales
 *     (CORS, autenticacion, rate limiting, circuit breakers, logging).</li>
 *     <li>Cada microservicio puede escalar, desplegarse o cambiar de puerto
 *     de forma independiente sin afectar a los consumidores del API.</li>
 * </ul>
 */
@Configuration
public class GatewayConfig {

    @Value("${services.atletas.url}")
    private String atletasServiceUrl;

    @Value("${services.biometria.url}")
    private String biometriaServiceUrl;

    @Value("${services.competencia.url}")
    private String competenciaServiceUrl;

    @Value("${services.contratos.url}")
    private String contratosServiceUrl;

    @Value("${services.entrenamiento.url}")
    private String entrenamientoServiceUrl;

    @Value("${services.inventario.url}")
    private String inventarioServiceUrl;

    @Value("${services.salud.url}")
    private String saludServiceUrl;

    @Value("${services.iam.url}")
    private String iamServiceUrl;

    @Value("${services.nutricion.url}")
    private String nutricionServiceUrl;

    @Value("${services.staff.url}")
    private String staffServiceUrl;

    /**
     * Define las reglas de enrutamiento (routes) del gateway.
     *
     * <p>Cada {@code route} asocia un patron de path publico
     * (por ejemplo {@code /api/atletas/**}) con la URI del microservicio
     * que debe atender esa peticion. Spring Cloud Gateway hace proxy
     * transparente de la peticion completa (metodo HTTP, headers, query
     * params y body) hacia el servicio de destino.</p>
     *
     * <p>Las URIs se inyectan via {@code application.properties} (valor por
     * defecto: {@code localhost:PUERTO}, valido cuando los 11 servicios
     * corren como procesos separados dentro del mismo contenedor
     * "polisport-app" de docker-compose) o {@code application-render.properties}
     * (perfil {@code render}: apuntan a la URL publica real de cada servicio
     * desplegado en Render). Esto evita tener que hardcodear en el codigo un
     * host que solo es valido para un entorno de despliegue especifico.</p>
     *
     * <p>Nota: los servicios {@code inventario} e {@code instalaciones} se
     * enrutan hacia el mismo microservicio ({@code inventario-service})
     * porque ambos dominios conviven dentro de ese mismo bounded context.</p>
     *
     * @param builder builder inyectado por Spring Cloud Gateway usado para
     *                construir la coleccion de rutas de forma fluida
     * @return el {@link RouteLocator} con la tabla de ruteo hacia los 10
     *         microservicios de negocio de la plataforma
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Gestion de atletas (fichas, datos personales y deportivos)
                .route("atletas", r -> r.path("/api/atletas/**")
                        .uri(atletasServiceUrl))
                // Registros biometricos asociados a cada atleta
                .route("biometria", r -> r.path("/api/v1/biometria/**")
                        .uri(biometriaServiceUrl))
                // Competencias deportivas y participacion de atletas
                .route("competencias", r -> r.path("/api/competencias/**")
                        .uri(competenciaServiceUrl))
                // Contratos de empleados/staff del polideportivo
                .route("contratos", r -> r.path("/api/contratos/**")
                        .uri(contratosServiceUrl))
                // Planificacion y seguimiento de entrenamientos
                .route("entrenamientos", r -> r.path("/api/entrenamientos/**")
                        .uri(entrenamientoServiceUrl))
                // Inventario de equipamiento deportivo
                .route("inventario", r -> r.path("/api/v1/inventario/**")
                        .uri(inventarioServiceUrl))
                // Instalaciones deportivas: vive en el mismo servicio que inventario
                .route("instalaciones", r -> r.path("/api/v1/instalaciones/**")
                        .uri(inventarioServiceUrl))
                // Registros de salud/atenciones medicas de los atletas
                .route("salud", r -> r.path("/api/v1/salud/**")
                        .uri(saludServiceUrl))
                // Identidad y control de acceso: usuarios, roles y permisos
                .route("iam", r -> r.path("/api/v1/iam/**")
                        .uri(iamServiceUrl))
                // Planes y seguimiento nutricional
                .route("nutricion", r -> r.path("/api/v1/nutricion/**")
                        .uri(nutricionServiceUrl))
                // Personal (staff) del polideportivo y sus roles internos
                .route("staff", r -> r.path("/api/v1/staff/**")
                        .uri(staffServiceUrl))
                .build();
    }
}
