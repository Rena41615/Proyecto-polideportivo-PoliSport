package com.polisport.competencia.client;

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
     * Ejemplo: Consulta a atletas-service para validar un participante.
     */
    public Mono<String> validarAtletaParaCompetencia(String atletaId) {
        return webClient.get()
                // Asumiendo que atletas-service corre en el puerto 8081
                .uri("http://localhost:8081/api/atletas/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                // Si el atleta no existe o el servicio de atletas está caído
                .onErrorReturn("Error: Atleta no encontrado o servicio no disponible.");
    }
}