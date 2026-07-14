package com.polisport.competencia.service;

import com.polisport.competencia.model.Categoria;
import com.polisport.competencia.model.Competencia;
import com.polisport.competencia.model.EstadoCompetencia;
import com.polisport.competencia.model.Modalidad;
import com.polisport.competencia.repository.CompetenciaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias para {@link CompetenciaService}, utilizando Mockito puro
 * (sin levantar el contexto de Spring) para verificar la interaccion con
 * {@link CompetenciaRepository} y el comportamiento de cada metodo publico.
 */
@ExtendWith(MockitoExtension.class)
class CompetenciaServiceTest {

    @Mock
    private CompetenciaRepository competenciaRepository;

    @InjectMocks
    private CompetenciaService competenciaService;

    private Competencia crearCompetencia(Long id) {
        Competencia competencia = new Competencia();
        competencia.setId(id);
        competencia.setNombreCompetencia("Campeonato Nacional de Atletismo 2026");
        competencia.setLugarCompetencia("Estadio Nacional, Santiago");
        competencia.setFechaInicio(LocalDate.of(2026, 7, 15));
        competencia.setFechaFin(LocalDate.of(2026, 7, 17));
        competencia.setCategoria(Categoria.SUB18);
        competencia.setModalidad(Modalidad.INDIVIDUAL);
        competencia.setEstadoCompetencia(EstadoCompetencia.PROGRAMADA);
        competencia.setInscritosRun(List.of(20456789, 21567890));
        return competencia;
    }

    // ---------- obtenerTodas ----------

    @Test
    void deberiaRetornarListaDeCompetenciasCuandoExistenRegistros() {
        List<Competencia> competencias = List.of(crearCompetencia(1L), crearCompetencia(2L));
        when(competenciaRepository.findAll()).thenReturn(competencias);

        List<Competencia> resultado = competenciaService.obtenerTodas();

        assertEquals(2, resultado.size());
        assertEquals(competencias, resultado);
        verify(competenciaRepository, times(1)).findAll();
    }

    @Test
    void deberiaRetornarListaVaciaCuandoNoHayCompetenciasRegistradas() {
        when(competenciaRepository.findAll()).thenReturn(Collections.emptyList());

        List<Competencia> resultado = competenciaService.obtenerTodas();

        assertTrue(resultado.isEmpty());
        verify(competenciaRepository, times(1)).findAll();
    }

    // ---------- obtenerPorId ----------

    @Test
    void deberiaRetornarCompetenciaCuandoExisteElId() {
        Competencia competencia = crearCompetencia(1L);
        when(competenciaRepository.findById(1L)).thenReturn(Optional.of(competencia));

        Optional<Competencia> resultado = competenciaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(competencia, resultado.get());
        verify(competenciaRepository, times(1)).findById(1L);
    }

    @Test
    void deberiaRetornarOptionalVacioCuandoNoExisteElId() {
        when(competenciaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Competencia> resultado = competenciaService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
        verify(competenciaRepository, times(1)).findById(99L);
    }

    // ---------- guardar ----------

    @Test
    void deberiaGuardarCompetenciaYRetornarLaEntidadPersistida() {
        Competencia competencia = crearCompetencia(null);
        Competencia competenciaGuardada = crearCompetencia(1L);
        when(competenciaRepository.save(competencia)).thenReturn(competenciaGuardada);

        Competencia resultado = competenciaService.guardar(competencia);

        assertEquals(1L, resultado.getId());
        assertEquals("Campeonato Nacional de Atletismo 2026", resultado.getNombreCompetencia());
        verify(competenciaRepository, times(1)).save(competencia);
    }

    @Test
    void deberiaActualizarCompetenciaExistenteAlGuardarConId() {
        Competencia competenciaExistente = crearCompetencia(1L);
        competenciaExistente.setLugarCompetencia("Estadio Municipal, Concepcion");
        when(competenciaRepository.save(competenciaExistente)).thenReturn(competenciaExistente);

        Competencia resultado = competenciaService.guardar(competenciaExistente);

        assertEquals("Estadio Municipal, Concepcion", resultado.getLugarCompetencia());
        verify(competenciaRepository, times(1)).save(competenciaExistente);
    }

    // ---------- eliminar ----------

    @Test
    void deberiaEliminarCompetenciaCuandoExisteElId() {
        doNothing().when(competenciaRepository).deleteById(1L);

        competenciaService.eliminar(1L);

        verify(competenciaRepository, times(1)).deleteById(1L);
    }

    @Test
    void deberiaLanzarExcepcionAlEliminarCompetenciaInexistente() {
        doThrow(new EmptyResultDataAccessException(1)).when(competenciaRepository).deleteById(99L);

        assertThrows(EmptyResultDataAccessException.class, () -> competenciaService.eliminar(99L));

        verify(competenciaRepository, times(1)).deleteById(99L);
    }

    // ---------- listarPorAtleta ----------

    @Test
    void deberiaRetornarCompetenciasCuandoElAtletaEstaInscrito() {
        Integer run = 20456789;
        List<Competencia> competencias = List.of(crearCompetencia(1L));
        when(competenciaRepository.buscarPorAtletaInscrito(run)).thenReturn(competencias);

        List<Competencia> resultado = competenciaService.listarPorAtleta(run);

        assertEquals(1, resultado.size());
        assertEquals(competencias, resultado);
        verify(competenciaRepository, times(1)).buscarPorAtletaInscrito(run);
    }

    @Test
    void deberiaRetornarListaVaciaCuandoElAtletaNoEstaInscritoEnNingunaCompetencia() {
        Integer run = 99999999;
        when(competenciaRepository.buscarPorAtletaInscrito(run)).thenReturn(Collections.emptyList());

        List<Competencia> resultado = competenciaService.listarPorAtleta(run);

        assertTrue(resultado.isEmpty());
        verify(competenciaRepository, times(1)).buscarPorAtletaInscrito(run);
    }
}
