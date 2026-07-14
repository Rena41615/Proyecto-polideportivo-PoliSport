package com.polisport.iam.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Cliente HTTP reactivo utilizado por el modulo de identidad y control de acceso (IAM)
 * para comunicarse con otros microservicios del ecosistema PoliSport.
 *
 * <p>Encapsula un {@link WebClient} configurado externamente ({@code ClientConfig}) y expone
 * operaciones basicas GET/POST que devuelven {@link Mono}, permitiendo a los servicios de este
 * paquete delegar en otros microservicios sin acoplarse directamente a la API de WebClient.</p>
 */
@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    /**
     * Crea el cliente HTTP reactivo a partir de un {@link WebClient} ya configurado
     * (por ejemplo, con la URL base del microservicio destino).
     *
     * @param webClient instancia de WebClient inyectada por Spring
     */
    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Realiza una peticion HTTP GET reactiva a la URL indicada y devuelve el cuerpo
     * de la respuesta como texto plano.
     *
     * @param url URL completa del recurso a consultar
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String}
     */
    public Mono<String> obtenerDatos(String url) {
        log.info("Realizando peticion GET a: {}", url);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta y cualquier error de forma reactiva, sin bloquear el flujo
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error en la peticion: {}", error.getMessage()));
    }

    /**
     * Realiza una peticion HTTP POST reactiva enviando {@code datos} como cuerpo de la
     * peticion a la URL indicada, y devuelve el cuerpo de la respuesta como texto plano.
     *
     * @param url URL completa del recurso destino
     * @param datos objeto a serializar y enviar como cuerpo de la peticion
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String}
     */
    public Mono<String> enviarDatos(String url, Object datos) {
        log.info("Enviando datos a: {}", url);
        return webClient.post()
                .uri(url)
                .bodyValue(datos)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta y cualquier error de forma reactiva, sin bloquear el flujo
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error al enviar datos: {}", error.getMessage()));
    }
}
