package com.polisport.inventario.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Cliente HTTP reactivo generico para la comunicacion entre microservicios.
 * <p>
 * Encapsula un {@link WebClient} y expone operaciones basicas GET/POST que
 * devuelven {@link Mono}, permitiendo que otros servicios del dominio de
 * inventario consulten o notifiquen datos a microservicios externos
 * (por ejemplo, instalaciones o reservas) sin acoplarse directamente a la
 * configuracion de WebClient.
 * </p>
 */
@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    /**
     * Crea el cliente a partir de una instancia de {@link WebClient}
     * ya configurada (inyectada por Spring).
     *
     * @param webClient cliente HTTP reactivo utilizado para las peticiones
     */
    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Realiza una peticion HTTP GET al endpoint indicado y devuelve el
     * cuerpo de la respuesta como texto.
     *
     * @param endpoint URL completa del recurso a consultar
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String};
     *         se completa con error si la peticion falla
     */
    public Mono<String> obtenerDatos(String endpoint) {
        log.info("Realizando peticion GET a: {}", endpoint);
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta exitosa para trazabilidad
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                // Se registra el error sin interrumpir la propagacion reactiva
                .doOnError(error -> log.error("Error en la peticion: {}", error.getMessage()));
    }

    /**
     * Realiza una peticion HTTP POST enviando el objeto recibido como cuerpo
     * de la peticion al endpoint indicado.
     *
     * @param endpoint URL completa del recurso destino
     * @param datos objeto que se serializa y envia como cuerpo de la peticion
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String};
     *         se completa con error si la peticion falla
     */
    public Mono<String> enviarDatos(String endpoint, Object datos) {
        log.info("Enviando datos a: {}", endpoint);
        return webClient.post()
                .uri(endpoint)
                .bodyValue(datos)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta exitosa para trazabilidad
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                // Se registra el error sin interrumpir la propagacion reactiva
                .doOnError(error -> log.error("Error al enviar datos: {}", error.getMessage()));
    }
}
