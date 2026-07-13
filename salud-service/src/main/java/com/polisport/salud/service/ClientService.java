package com.polisport.salud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * ClientService: cliente reactivo genérico para la comunicación HTTP entre
 * microservicios dentro del dominio de salud.
 * <p>
 * Encapsula un {@link WebClient} configurado en {@code ClientConfig} y expone
 * operaciones básicas (GET y POST) que devuelven {@link Mono}, permitiendo a
 * otros componentes del microservicio consultar o notificar a servicios
 * externos (por ejemplo, atleta-service u otros) sin acoplarse directamente
 * a la API reactiva de Spring WebFlux.
 */
@Slf4j
@Service
public class ClientService {

    private final WebClient webClient;

    /**
     * Crea el servicio a partir de una instancia de {@link WebClient} ya
     * configurada (timeouts, conector, etc.) inyectada por Spring.
     *
     * @param webClient cliente HTTP reactivo utilizado para las peticiones salientes
     */
    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Realiza una petición HTTP GET de forma reactiva hacia la URL indicada
     * y devuelve el cuerpo de la respuesta como texto.
     * <p>
     * La operación es perezosa (lazy): la petición solo se ejecuta cuando el
     * {@link Mono} devuelto es suscrito (por ejemplo, mediante {@code .block()}
     * o dentro de un flujo reactivo).
     *
     * @param url URL completa del recurso a consultar
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String},
     *         o un error si la petición falla
     */
    public Mono<String> obtenerDatos(String url) {
        log.info("Realizando peticion GET a: {}", url);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta exitosa antes de propagarla al suscriptor
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                // Se registra el error sin interrumpir la propagación de la excepción
                .doOnError(error -> log.error("Error en la peticion: {}", error.getMessage()));
    }

    /**
     * Realiza una petición HTTP POST de forma reactiva hacia la URL indicada,
     * enviando el objeto dado como cuerpo de la petición.
     * <p>
     * Al igual que {@link #obtenerDatos(String)}, la ejecución es perezosa y
     * depende de la suscripción del {@link Mono} resultante.
     *
     * @param url   URL completa del recurso destino
     * @param datos objeto a serializar y enviar como cuerpo de la petición
     * @return {@link Mono} que emite el cuerpo de la respuesta como {@link String},
     *         o un error si la petición falla
     */
    public Mono<String> enviarDatos(String url, Object datos) {
        log.info("Enviando datos a: {}", url);
        return webClient.post()
                .uri(url)
                .bodyValue(datos)
                .retrieve()
                .bodyToMono(String.class)
                // Se registra la respuesta exitosa antes de propagarla al suscriptor
                .doOnNext(respuesta -> log.info("Respuesta recibida: {}", respuesta))
                // Se registra el error sin interrumpir la propagación de la excepción
                .doOnError(error -> log.error("Error al enviar datos: {}", error.getMessage()));
    }
}
