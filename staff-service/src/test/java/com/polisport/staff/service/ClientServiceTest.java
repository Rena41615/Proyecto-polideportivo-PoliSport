package com.polisport.staff.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Pruebas unitarias de {@link ClientService}.
 * <p>
 * Se mockea la cadena fluida de {@link WebClient} para verificar que las
 * operaciones GET y POST invocan correctamente los metodos esperados y
 * propagan el cuerpo de la respuesta a traves del {@link Mono} resultante.
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
    void shouldPropagateErrorOnGet() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.error(new RuntimeException("fallo de conexion")));

        RuntimeException excepcion = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> clientService.obtenerDatos("http://servicio/api").block());

        assertEquals("fallo de conexion", excepcion.getMessage());
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
    void shouldPropagateErrorOnPost() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.error(new RuntimeException("fallo al enviar")));

        RuntimeException excepcion = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> clientService.enviarDatos("http://servicio/api", new Object()).block());

        assertEquals("fallo al enviar", excepcion.getMessage());
    }
}
