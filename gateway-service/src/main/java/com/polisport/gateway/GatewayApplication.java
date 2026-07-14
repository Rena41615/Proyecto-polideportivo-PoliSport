package com.polisport.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada del API Gateway (gateway-service).
 *
 * <p>Levanta el unico servicio publico de la plataforma PoliSport
 * (puerto {@code 8080}), encargado de enrutar cada peticion entrante hacia
 * el microservicio interno correspondiente. Ver {@link
 * com.polisport.gateway.config.GatewayConfig} para el detalle de las
 * rutas configuradas hacia cada uno de los 10 microservicios de negocio.</p>
 */
@SpringBootApplication
public class GatewayApplication {

    /**
     * Arranca el contexto de Spring Boot del gateway.
     *
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
