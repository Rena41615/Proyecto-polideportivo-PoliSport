package com.polisport.entrenamiento.client;

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
     * Ejemplo: Consulta a salud-service para verificar restricciones médicas
     * del atleta antes de permitir la asignación de una rutina pesada.
     */
    public Mono<String> verificarRestriccionesMedicas(String atletaId) {
        return webClient.get()
                // Asumiendo que salud-service corre en el puerto 8089
                .uri("http://localhost:8089/api/salud/atleta/{id}/restricciones", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                // Si el servicio está caído o no responde, asumimos por seguridad que no hay datos
                .onErrorReturn("No se pudo verificar el estado médico. Proceder con precaución.");
    }
}