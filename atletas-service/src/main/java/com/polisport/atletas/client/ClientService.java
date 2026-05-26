package com.polisport.atletas.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * ClientService: Cliente para comunicación entre microservicios.
 * Utiliza WebClient reactivo con manejo de errores y timeouts.
 */
@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Obtener datos de salud de un atleta desde salud-service.
     * Maneja timeouts y errores de forma resiliente.
     */
    public Mono<String> obtenerDatosSalud(String atletaId) {
        log.info("Realizando petición a salud-service para atleta: {}", atletaId);
        
        return webClient.get()
                .uri("http://salud-service:8080/api/salud/atleta/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .doOnSuccess(respuesta -> log.info("Respuesta exitosa de salud-service: {}", respuesta))
                .doOnError(error -> log.error("Error en petición a salud-service: {}", error.getMessage()))
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientResponseException.NotFound) {
                        log.warn("Recurso no encontrado en salud-service");
                        return Mono.just("Datos de salud no encontrados");
                    } else if (ex instanceof WebClientResponseException) {
                        log.error("Error HTTP en salud-service: {}", ((WebClientResponseException) ex).getStatusCode());
                        return Mono.just("Error al consultar salud-service");
                    } else {
                        log.error("Timeout o error de conexión con salud-service");
                        return Mono.just("Servicio de salud no disponible en este momento");
                    }
                });
    }

    /**
     * Obtener ficha consolidada: Salud + Entrenamiento.
     * Encadena múltiples llamadas de forma reactiva.
     */
    public Mono<String> obtenerFichaConsolidada(String atletaId) {
        log.info("Obteniendo ficha consolidada para atleta: {}", atletaId);
        
        return webClient.get()
                .uri("http://salud-service:8080/api/salud/atleta/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .doOnError(error -> log.error("Error obteniendo datos de salud: {}", error.getMessage()))
                .onErrorReturn("Sin datos de salud disponibles")
                .flatMap(infoSalud -> {
                    log.info("Datos de salud obtenidos, consultando entrenamiento...");
                    
                    return webClient.get()
                            .uri("http://entrenamiento-service:8080/api/entrenamiento/atleta/{id}", atletaId)
                            .retrieve()
                            .bodyToMono(String.class)
                            .timeout(Duration.ofSeconds(10))
                            .doOnError(error -> log.error("Error obteniendo datos de entrenamiento: {}", error.getMessage()))
                            .onErrorReturn("Sin plan de entrenamiento disponible")
                            .map(infoEntrenamiento -> {
                                log.info("Ficha consolidada completada para atleta: {}", atletaId);
                                return "=== FICHA DEL ATLETA ===\n" +
                                        "ID Atleta: " + atletaId + "\n" +
                                        "Salud: " + infoSalud + "\n" +
                                        "Entrenamiento: " + infoEntrenamiento;
                            });
                });
    }

    /**
     * Obtener datos de nutrición de un atleta.
     */
    public Mono<String> obtenerDatosNutricion(String atletaId) {
        log.info("Realizando petición a nutricion-service para atleta: {}", atletaId);
        
        return webClient.get()
                .uri("http://nutricion-service:8080/api/v1/nutricion/atleta/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .doOnSuccess(respuesta -> log.info("Respuesta exitosa de nutricion-service"))
                .doOnError(error -> log.error("Error en petición a nutricion-service: {}", error.getMessage()))
                .onErrorReturn("Datos de nutrición no disponibles");
    }

    /**
     * Obtener información de competencias para un atleta.
     */
    public Mono<String> obtenerCompetencias(String atletaId) {
        log.info("Realizando petición a competencia-service para atleta: {}", atletaId);
        
        return webClient.get()
                .uri("http://competencia-service:8080/api/competencias/atleta/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .doOnSuccess(respuesta -> log.info("Respuesta exitosa de competencia-service"))
                .doOnError(error -> log.error("Error en petición a competencia-service: {}", error.getMessage()))
                .onErrorReturn("Información de competencias no disponible");
    }
}
