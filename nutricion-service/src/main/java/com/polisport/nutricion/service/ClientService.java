package com.polisport.nutricion.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Cliente HTTP reactivo utilizado por el microservicio de nutricion para
 * comunicarse con otros microservicios de la plataforma PoliSport (por ejemplo,
 * para validar la existencia de un atleta antes de crear un plan nutricional).
 * <p>
 * Encapsula el uso de {@link WebClient} para realizar peticiones GET y POST
 * de forma no bloqueante, devolviendo los resultados como {@link Mono}.
 */
@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    /**
     * Crea el cliente reactivo a partir de una instancia de {@link WebClient}
     * ya configurada (inyectada por el contenedor de Spring).
     *
     * @param webClient cliente HTTP reactivo utilizado para realizar las peticiones
     */
    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Realiza una peticion GET de forma reactiva a la URL indicada y devuelve
     * el cuerpo de la respuesta como texto.
     *
     * @param url URL completa del recurso a consultar en el microservicio destino
     * @return un {@link Mono} que emite el cuerpo de la respuesta como {@link String};
     *         en caso de error en la peticion, el error se registra en el log y se
     *         propaga a traves del {@link Mono}
     */
    public Mono<String> obtenerDatos(String url) {
        log.info("Realizando peticion GET a: {}", url);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta o el error sin alterar el flujo reactivo
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error en la peticion: {}", error.getMessage()));
    }

    /**
     * Realiza una peticion POST de forma reactiva enviando el objeto indicado
     * como cuerpo de la solicitud, y devuelve el cuerpo de la respuesta como texto.
     *
     * @param url   URL completa del recurso destino en el microservicio remoto
     * @param datos objeto a serializar y enviar como cuerpo de la peticion
     * @return un {@link Mono} que emite el cuerpo de la respuesta como {@link String};
     *         en caso de error en la peticion, el error se registra en el log y se
     *         propaga a traves del {@link Mono}
     */
    public Mono<String> enviarDatos(String url, Object datos) {
        log.info("Enviando datos a: {}", url);
        return webClient.post()
                .uri(url)
                .bodyValue(datos)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta o el error sin alterar el flujo reactivo
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                .doOnError(error -> log.error("Error al enviar datos: {}", error.getMessage()));
    }
}
