package com.polisport.inventario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link ClientService}, verificando el ensamblado de
 * la cadena reactiva de {@link WebClient} para peticiones GET y POST, tanto
 * en el camino exitoso como en el de error.
 */
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock private WebClient webClient;
    @Mock private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock private WebClient.RequestBodySpec requestBodySpec;
    @Mock private WebClient.ResponseSpec responseSpec;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(webClient);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldGetDataSuccessfully() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("respuesta"));

        String resultado = clientService.obtenerDatos("http://servicio/api").block();

        assertEquals("respuesta", resultado);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldGetDataOnError() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.error(new RuntimeException("fallo de conexion")));

        Mono<String> resultado = clientService.obtenerDatos("http://servicio/api");

        assertThrows(RuntimeException.class, resultado::block);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldPostDataSuccessfully() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("ok"));

        String resultado = clientService.enviarDatos("http://servicio/api", new Object()).block();

        assertEquals("ok", resultado);
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldPostDataOnError() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.error(new RuntimeException("fallo al enviar")));

        Mono<String> resultado = clientService.enviarDatos("http://servicio/api", new Object());

        assertThrows(RuntimeException.class, resultado::block);
    }
}
