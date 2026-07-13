package com.polisport.atletas.service;

import com.polisport.atletas.exception.ResourceNotFoundException;
import com.polisport.atletas.model.Atleta;
import com.polisport.atletas.repository.AtletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para {@link AtletaService} usando Mockito puro (sin contexto de Spring),
 * de modo que la ejecucion sea rapida y solo se verifique la logica del servicio, aislando
 * el {@link AtletaRepository} mediante mocks.
 */
@ExtendWith(MockitoExtension.class)
class AtletaServiceTest {

    @Mock
    private AtletaRepository atletaRepository;

    @InjectMocks
    private AtletaService atletaService;

    private Atleta atleta;

    @BeforeEach
    void setUp() {
        atleta = new Atleta();
        atleta.setId(1L);
        atleta.setRunAtleta(20456789);
        atleta.setDvrunAtleta("K");
        atleta.setPrimerNombre("Juan");
        atleta.setSegundoNombre("Carlos");
        atleta.setTercerNombre("Andres");
        atleta.setPrimerApellido("Perez");
        atleta.setSegundoApellido("Garcia");
        atleta.setEmail("juan.perez@polisport.cl");
        atleta.setFechaNacimiento(LocalDate.of(1998, 5, 15));
        atleta.setDeportePrincipal("Atletismo");
        atleta.setCategoria("Junior");
        atleta.setHistorialDeportivo("Campeon nacional 2023");
    }

    @Test
    void shouldReturnAllAtletasWhenObtenerTodos() {
        when(atletaRepository.findAll()).thenReturn(List.of(atleta));

        List<Atleta> resultado = atletaService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals(atleta, resultado.get(0));
        verify(atletaRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenObtenerTodosSinRegistros() {
        when(atletaRepository.findAll()).thenReturn(List.of());

        List<Atleta> resultado = atletaService.obtenerTodos();

        assertTrue(resultado.isEmpty());
    }

    @Test
    void shouldReturnAtletaWhenObtenerPorIdExiste() {
        when(atletaRepository.findById(1L)).thenReturn(Optional.of(atleta));

        Optional<Atleta> resultado = atletaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getPrimerNombre());
    }

    @Test
    void shouldReturnEmptyWhenObtenerPorIdNoExiste() {
        when(atletaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Atleta> resultado = atletaService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldSaveAtletaWhenGuardarAtleta() {
        when(atletaRepository.save(any(Atleta.class))).thenReturn(atleta);

        Atleta resultado = atletaService.guardarAtleta(atleta);

        assertNotNull(resultado);
        assertEquals(20456789, resultado.getRunAtleta());
        verify(atletaRepository, times(1)).save(atleta);
    }

    @Test
    void shouldReturnAtletaWhenObtenerPorRunExiste() {
        when(atletaRepository.findByRunAtleta(20456789)).thenReturn(Optional.of(atleta));

        Optional<Atleta> resultado = atletaService.obtenerPorRun(20456789);

        assertTrue(resultado.isPresent());
        assertEquals("Perez", resultado.get().getPrimerApellido());
    }

    @Test
    void shouldReturnEmptyWhenObtenerPorRunNoExiste() {
        when(atletaRepository.findByRunAtleta(11111111)).thenReturn(Optional.empty());

        Optional<Atleta> resultado = atletaService.obtenerPorRun(11111111);

        assertFalse(resultado.isPresent());
    }

    @Test
    void shouldUpdateNombresYApellidosWhenActualizarParcial() {
        when(atletaRepository.findById(1L)).thenReturn(Optional.of(atleta));
        when(atletaRepository.save(any(Atleta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Map<String, Object> updates = new HashMap<>();
        updates.put("primerNombre", "Pedro");
        updates.put("segundoNombre", "Luis");
        updates.put("tercerNombre", "Ignacio");
        updates.put("primerApellido", "Soto");
        updates.put("segundoApellido", "Rojas");

        Atleta resultado = atletaService.actualizarParcial(1L, updates);

        assertEquals("Pedro", resultado.getPrimerNombre());
        assertEquals("Luis", resultado.getSegundoNombre());
        assertEquals("Ignacio", resultado.getTercerNombre());
        assertEquals("Soto", resultado.getPrimerApellido());
        assertEquals("Rojas", resultado.getSegundoApellido());
        verify(atletaRepository, times(1)).save(atleta);
    }

    @Test
    void shouldUpdateDatosDeportivosWhenActualizarParcial() {
        when(atletaRepository.findById(1L)).thenReturn(Optional.of(atleta));
        when(atletaRepository.save(any(Atleta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Map<String, Object> updates = new HashMap<>();
        updates.put("email", "nuevo.email@polisport.cl");
        updates.put("deportePrincipal", "Natacion");
        updates.put("categoria", "Senior");
        updates.put("historialDeportivo", "Subcampeon sudamericano 2022");

        Atleta resultado = atletaService.actualizarParcial(1L, updates);

        assertEquals("nuevo.email@polisport.cl", resultado.getEmail());
        assertEquals("Natacion", resultado.getDeportePrincipal());
        assertEquals("Senior", resultado.getCategoria());
        assertEquals("Subcampeon sudamericano 2022", resultado.getHistorialDeportivo());
    }

    @Test
    void shouldIgnoreCampoDesconocidoWhenActualizarParcial() {
        when(atletaRepository.findById(1L)).thenReturn(Optional.of(atleta));
        when(atletaRepository.save(any(Atleta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Map<String, Object> updates = new HashMap<>();
        updates.put("campoInexistente", "valorIrrelevante");

        Atleta resultado = atletaService.actualizarParcial(1L, updates);

        // El campo desconocido no rompe la ejecucion y el resto de los datos no cambia
        assertEquals("Juan", resultado.getPrimerNombre());
        verify(atletaRepository, times(1)).save(atleta);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenActualizarParcialIdNoExiste() {
        when(atletaRepository.findById(99L)).thenReturn(Optional.empty());

        Map<String, Object> updates = new HashMap<>();
        updates.put("primerNombre", "Pedro");

        assertThrows(ResourceNotFoundException.class,
                () -> atletaService.actualizarParcial(99L, updates));

        verify(atletaRepository, never()).save(any(Atleta.class));
    }
}
