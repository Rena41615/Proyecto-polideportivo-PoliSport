package com.polisport.inventario.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> obtenerDatos(String endpoint) {
        log.info("Realizando petición GET a: {}", endpoint);
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error en la petición: {}", error.getMessage()));
    }

    public Mono<String> enviarDatos(String endpoint, Object datos) {
        log.info("Enviando datos a: {}", endpoint);
        return webClient.post()
                .uri(endpoint)
                .bodyValue(datos)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error al enviar datos: {}", error.getMessage()));
    }
}
