package com.polisport.salud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> obtenerDatos(String url) {
        log.info("Realizando peticion GET a: {}", url);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error en la peticion: {}", error.getMessage()));
    }

    public Mono<String> enviarDatos(String url, Object datos) {
        log.info("Enviando datos a: {}", url);
        return webClient.post()
                .uri(url)
                .bodyValue(datos)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error al enviar datos: {}", error.getMessage()));
    }
}
