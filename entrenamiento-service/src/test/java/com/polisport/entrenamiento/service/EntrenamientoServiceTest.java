package com.polisport.entrenamiento.service;

import com.polisport.entrenamiento.model.Entrenamiento;
import com.polisport.entrenamiento.model.EstadoEntrenamiento;
import com.polisport.entrenamiento.model.TipoEntrenamiento;
import com.polisport.entrenamiento.repository.EntrenamientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link EntrenamientoService} con Mockito puro.
 * <p>
 * No se levanta el contexto de Spring: el repositorio se simula con {@code @Mock}
 * para validar unicamente la logica del servicio de forma rapida y aislada.
 */
@ExtendWith(MockitoExtension.class)
class EntrenamientoServiceTest {

    @Mock
    private EntrenamientoRepository entrenamientoRepository;

    @InjectMocks
    private EntrenamientoService entrenamientoService;

    private Entrenamiento entrenamiento;

    @BeforeEach
    void setUp() {
        entrenamiento = crearEntrenamiento(1L, 15234789, "2");
    }

    private Entrenamiento crearEntrenamiento(Long id, Integer runEntrenador, String dv) {
        Entrenamiento e = new Entrenamiento();
        e.setId(id);
        e.setRunEntrenador(runEntrenador);
        e.setDvrunEntrenador(dv);
        e.setAtletasParticipantes(Arrays.asList(19876543, 20123456));
        e.setFecha(LocalDate.of(2025, 6, 15));
        e.setHoraInicio(LocalTime.of(8, 0));
        e.setHoraFin(LocalTime.of(10, 0));
        e.setTipoEntrenamiento(TipoEntrenamiento.FUNCIONAL);
        e.setEstado(EstadoEntrenamiento.PLANIFICADO);
        e.setObservaciones("Sesion de prueba");
        e.setNivelIntensidad(7.5);
        e.setDuracionMinutos(120);
        return e;
    }

    // ---------------------------------------------------------------
    // obtenerTodos
    // ---------------------------------------------------------------

    @Test
    void deberiaRetornarListaDeEntrenamientosCuandoExistenRegistros() {
        Entrenamiento otro = crearEntrenamiento(2L, 16111222, "5");
        when(entrenamientoRepository.findAll()).thenReturn(Arrays.asList(entrenamiento, otro));

        List<Entrenamiento> resultado = entrenamientoService.obtenerTodos();

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(entrenamiento));
        verify(entrenamientoRepository, times(1)).findAll();
    }

    @Test
    void deberiaRetornarListaVaciaCuandoNoHayEntrenamientosRegistrados() {
        when(entrenamientoRepository.findAll()).thenReturn(Collections.emptyList());

        List<Entrenamiento> resultado = entrenamientoService.obtenerTodos();

        assertTrue(resultado.isEmpty());
        verify(entrenamientoRepository, times(1)).findAll();
    }

    // ---------------------------------------------------------------
    // obtenerPorId
    // ---------------------------------------------------------------

    @Test
    void deberiaRetornarEntrenamientoCuandoIdExiste() {
        when(entrenamientoRepository.findById(1L)).thenReturn(Optional.of(entrenamiento));

        Optional<Entrenamiento> resultado = entrenamientoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(entrenamientoRepository, times(1)).findById(1L);
    }

    @Test
    void deberiaRetornarOptionalVacioCuandoIdNoExiste() {
        when(entrenamientoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Entrenamiento> resultado = entrenamientoService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
        verify(entrenamientoRepository, times(1)).findById(99L);
    }

    // ---------------------------------------------------------------
    // guardar
    // ---------------------------------------------------------------

    @Test
    void deberiaGuardarYRetornarEntrenamientoCuandoDatosSonValidos() {
        when(entrenamientoRepository.save(entrenamiento)).thenReturn(entrenamiento);

        Entrenamiento resultado = entrenamientoService.guardar(entrenamiento);

        assertThat(resultado).isNotNull();
        assertEquals(entrenamiento.getRunEntrenador(), resultado.getRunEntrenador());
        verify(entrenamientoRepository, times(1)).save(entrenamiento);
    }

    @Test
    void deberiaAsignarIdGeneradoAlActualizarEntrenamientoNuevo() {
        Entrenamiento nuevo = crearEntrenamiento(null, 17111333, "9");
        Entrenamiento persistido = crearEntrenamiento(10L, 17111333, "9");
        when(entrenamientoRepository.save(nuevo)).thenReturn(persistido);

        Entrenamiento resultado = entrenamientoService.guardar(nuevo);

        assertEquals(10L, resultado.getId());
        verify(entrenamientoRepository, times(1)).save(nuevo);
    }

    // ---------------------------------------------------------------
    // eliminar
    // ---------------------------------------------------------------

    @Test
    void deberiaEliminarEntrenamientoCuandoIdExiste() {
        entrenamientoService.eliminar(1L);

        verify(entrenamientoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deberiaPropagarExcepcionCuandoSeEliminaEntrenamientoInexistente() {
        doThrow(new EmptyResultDataAccessException(1)).when(entrenamientoRepository).deleteById(anyLong());

        assertThrows(EmptyResultDataAccessException.class, () -> entrenamientoService.eliminar(99L));
    }

    // ---------------------------------------------------------------
    // listarPorAtleta
    // ---------------------------------------------------------------

    @Test
    void deberiaRetornarHistorialDeEntrenamientosCuandoAtletaTieneRegistros() {
        when(entrenamientoRepository.buscarPorAtleta(19876543)).thenReturn(Collections.singletonList(entrenamiento));

        List<Entrenamiento> resultado = entrenamientoService.listarPorAtleta(19876543);

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getAtletasParticipantes().contains(19876543));
        verify(entrenamientoRepository, times(1)).buscarPorAtleta(19876543);
    }

    @Test
    void deberiaRetornarListaVaciaCuandoAtletaNoTieneEntrenamientosRegistrados() {
        when(entrenamientoRepository.buscarPorAtleta(1111111)).thenReturn(Collections.emptyList());

        List<Entrenamiento> resultado = entrenamientoService.listarPorAtleta(1111111);

        assertTrue(resultado.isEmpty());
        verify(entrenamientoRepository, times(1)).buscarPorAtleta(1111111);
    }
}
