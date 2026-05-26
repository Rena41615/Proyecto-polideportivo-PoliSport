package com.polisport.salud.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * ClientConfig: Configuración de WebClient para comunicación entre microservicios.
 * Incluye timeouts, reintentos y manejo de conexiones.
 */
@Slf4j
@Configuration
public class ClientConfig {

    /**
     * WebClient bean con configuración de timeout y resilencia.
     * - Connection timeout: 5 segundos
     * - Read timeout: 10 segundos
     * - Write timeout: 10 segundos
     */
    @Bean
    public WebClient webClient() {
        log.info("Inicializando WebClient con timeouts configurados");
        
        // Configurar HttpClient con timeouts
        HttpClient httpClient = HttpClient.create()
                .secure(sslSpec -> sslSpec.sslContext(SslProvider.builder().build()))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 5 segundos
                .responseTimeout(Duration.ofSeconds(10)) // 10 segundos para read
                .doOnConnected(conn -> {
                    conn.addHandlerLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS));
                    conn.addHandlerLast(new WriteTimeoutHandler(10, TimeUnit.SECONDS));
                    log.debug("Handlers de timeout agregados a la conexión");
                });
        
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
