package com.polisport.contratos.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Ejemplo: Consulta a atletas-service para obtener los datos del deportista
     * y validar que existe antes de emitirle un contrato.
     */
    public Mono<String> validarAtletaParaContrato(String atletaId) {
        return webClient.get()
                // Asumiendo que atletas-service corre en el puerto 8081
                .uri("http://localhost:8081/api/atletas/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                // Respuesta de seguridad por si el servicio de atletas falla
                .onErrorReturn("Error: No se pudo validar la existencia del atleta para emitir el contrato.");
    }
}