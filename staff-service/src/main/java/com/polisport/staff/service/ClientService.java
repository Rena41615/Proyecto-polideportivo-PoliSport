package com.polisport.staff.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Cliente reactivo generico para la comunicacion HTTP entre el microservicio
 * de staff y el resto de microservicios del ecosistema PoliSport.
 * <p>
 * Encapsula un {@link WebClient} para exponer operaciones basicas de
 * lectura (GET) y escritura (POST) sin bloquear el hilo llamante, delegando
 * en el llamador la decision de suscribirse o bloquear el {@link Mono}
 * resultante.
 */
@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    /**
     * Crea el cliente a partir de un {@link WebClient} ya configurado
     * (base URL, filtros, etc.), normalmente inyectado por Spring.
     *
     * @param webClient instancia de WebClient utilizada para las llamadas HTTP
     */
    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Realiza una peticion HTTP GET a la URL indicada y devuelve el cuerpo
     * de la respuesta como texto.
     *
     * @param url URL completa del recurso a consultar en el microservicio destino
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String};
     *         se completa con error si la peticion falla
     */
    public Mono<String> obtenerDatos(String url) {
        log.info("Realizando peticion GET a: {}", url);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta exitosa sin alterar el flujo reactivo
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                // Se registra el error pero se propaga tal cual al suscriptor
                .doOnError(error -> log.error("Error en la peticion: {}", error.getMessage()));
    }

    /**
     * Realiza una peticion HTTP POST enviando el objeto indicado como cuerpo
     * de la solicitud y devuelve el cuerpo de la respuesta como texto.
     *
     * @param url   URL completa del recurso destino en el microservicio remoto
     * @param datos objeto que se serializa como cuerpo de la peticion
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String};
     *         se completa con error si la peticion falla
     */
    public Mono<String> enviarDatos(String url, Object datos) {
        log.info("Enviando datos a: {}", url);
        return webClient.post()
                .uri(url)
                .bodyValue(datos)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta exitosa sin alterar el flujo reactivo
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                // Se registra el error pero se propaga tal cual al suscriptor
                .doOnError(error -> log.error("Error al enviar datos: {}", error.getMessage()));
    }
}
