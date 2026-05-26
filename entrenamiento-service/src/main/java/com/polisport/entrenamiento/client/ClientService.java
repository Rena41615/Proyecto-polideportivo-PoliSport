package com.polisport.entrenamiento.client;

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
     * Realizar una petición GET genérica a otro microservicio.
     * Maneja timeouts y errores de forma resiliente.
     */
    public Mono<String> obtenerDatos(String url) {
        log.info("Realizando petición GET a: {}", url);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .doOnSuccess(respuesta -> log.info("Respuesta exitosa: {} caracteres", respuesta.length()))
                .doOnError(error -> log.error("Error en petición: {}", error.getMessage()))
                .onErrorResume(ex -> {
                    if (ex instanceof WebClientResponseException.NotFound) {
                        log.warn("Recurso no encontrado: {}", url);
                        return Mono.just("Recurso no encontrado");
                    } else if (ex instanceof WebClientResponseException) {
                        log.error("Error HTTP: {}", ((WebClientResponseException) ex).getStatusCode());
                        return Mono.just("Error al consultar el servicio");
                    } else {
                        log.error("Timeout o error de conexión");
                        return Mono.just("Servicio no disponible en este momento");
                    }
                });
    }

    /**
     * Realizar una petición POST genérica con body.
     * Maneja timeouts y errores.
     */
    public Mono<String> enviarDatos(String url, Object body) {
        log.info("Realizando petición POST a: {}", url);
        
        return webClient.post()
                .uri(url)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(10))
                .doOnSuccess(respuesta -> log.info("Datos enviados exitosamente"))
                .doOnError(error -> log.error("Error enviando datos: {}", error.getMessage()))
                .onErrorReturn("Error al enviar datos al servicio");
    }
}
