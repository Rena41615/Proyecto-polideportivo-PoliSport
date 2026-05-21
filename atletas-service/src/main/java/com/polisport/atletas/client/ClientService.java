package com.polisport.atletas.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    private final WebClient webClient;

    // Spring inyecta automáticamente el WebClient que creaste en tu ClientConfig
    public ClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Ejemplo 1: Consultar solo el estado de salud de un atleta (Llamada simple)
     */
    public Mono<String> obtenerDatosSalud(String atletaId) {
        return webClient.get()
                // Asumiendo que salud-service corre en el puerto 8089 (cámbialo si es otro)
                .uri("http://localhost:8089/api/salud/atleta/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                // Resiliencia: Si salud-service está caído, no se cae toda tu app, solo devuelve este mensaje
                .onErrorReturn("Servicio de salud no disponible en este momento.");
    }

    /**
     * Ejemplo 2: Consultar la ficha consolidada (Llamada encadenada y reactiva)
     * Va a buscar salud y, cuando termina, va a buscar entrenamiento.
     */
    public Mono<String> obtenerFichaConsolidada(String atletaId) {
        return webClient.get()
                .uri("http://localhost:8089/api/salud/atleta/{id}", atletaId)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Sin datos de salud")
                .flatMap(infoSalud -> {

                    // Una vez que tenemos la info de salud, encadenamos la llamada a entrenamientos
                    return webClient.get()
                            // Asumiendo que entrenamiento-service corre en el puerto 8086
                            .uri("http://localhost:8086/api/entrenamiento/atleta/{id}", atletaId)
                            .retrieve()
                            .bodyToMono(String.class)
                            .onErrorReturn("Sin plan de entrenamiento")
                            .map(infoEntrenamiento -> {

                                // Combinamos ambos resultados
                                return "=== FICHA DEL ATLETA ===\n" +
                                        "Salud: " + infoSalud + "\n" +
                                        "Entrenamiento: " + infoEntrenamiento;
                            });
                });
    }
}