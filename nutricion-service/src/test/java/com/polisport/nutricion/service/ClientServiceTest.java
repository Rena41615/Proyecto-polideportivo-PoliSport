package com.polisport.nutricion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link ClientService}, simulando la cadena fluida de
 * {@link WebClient} para verificar las peticiones GET y POST sin realizar
 * llamadas de red reales.
 */
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(webClient);
    }

    @Test
    @SuppressWarnings("unchecked")
    void obtenerDatos_deberiaRetornarLaRespuestaDelServicioRemoto() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("respuesta"));

        String resultado = clientService.obtenerDatos("http://servicio/api").block();

        assertEquals("respuesta", resultado);
    }

    @Test
    @SuppressWarnings("unchecked")
    void obtenerDatos_cuandoFalla_deberiaPropagarElError() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.error(new RuntimeException("fallo de conexion")));

        Mono<String> resultado = clientService.obtenerDatos("http://servicio/api");

        // Se verifica que el error se propague a traves del Mono sin ser silenciado
        try {
            resultado.block();
        } catch (RuntimeException ex) {
            assertEquals("fallo de conexion", ex.getMessage());
            return;
        }
        throw new AssertionError("Se esperaba que se propagara una excepcion");
    }

    @Test
    @SuppressWarnings("unchecked")
    void enviarDatos_deberiaRetornarLaRespuestaDelServicioRemoto() {
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("ok"));

        String resultado = clientService.enviarDatos("http://servicio/api", new Object()).block();

        assertEquals("ok", resultado);
    }
}
