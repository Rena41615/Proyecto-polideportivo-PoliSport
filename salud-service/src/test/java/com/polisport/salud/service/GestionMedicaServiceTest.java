package com.polisport.salud.service;

import com.polisport.salud.model.GestionMedica;
import com.polisport.salud.repository.GestionMedicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link GestionMedicaService}, aislando el repositorio
 * mediante Mockito para verificar cada operación CRUD y sus distintos casos.
 */
@ExtendWith(MockitoExtension.class)
class GestionMedicaServiceTest {

    @Mock
    private GestionMedicaRepository gestionMedicaRepository;

    @InjectMocks
    private GestionMedicaService gestionMedicaService;

    private GestionMedica gestionMedica;

    @BeforeEach
    void setUp() {
        gestionMedica = new GestionMedica(
                1L,
                25L,
                "Esguince de tobillo",
                LocalDate.of(2025, 6, 10),
                "Esguince grado II en tobillo derecho con inflamacion moderada",
                "2025-07-10",
                "EN_RECUPERACION",
                10L,
                "Reposo, hielo, compresion, elevacion y fisioterapia",
                "Responde bien al tratamiento"
        );
    }

    @Test
    void listarDebeRetornarTodosLosRegistros() {
        GestionMedica otro = new GestionMedica();
        otro.setId(2L);
        when(gestionMedicaRepository.findAll()).thenReturn(Arrays.asList(gestionMedica, otro));

        List<GestionMedica> resultado = gestionMedicaService.listar();

        assertEquals(2, resultado.size());
        verify(gestionMedicaRepository, times(1)).findAll();
    }

    @Test
    void listarDebeRetornarListaVaciaSiNoHayRegistros() {
        when(gestionMedicaRepository.findAll()).thenReturn(Collections.emptyList());

        List<GestionMedica> resultado = gestionMedicaService.listar();

        assertTrue(resultado.isEmpty());
        verify(gestionMedicaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorIdDebeRetornarRegistroCuandoExiste() {
        when(gestionMedicaRepository.findById(1L)).thenReturn(Optional.of(gestionMedica));

        Optional<GestionMedica> resultado = gestionMedicaService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(gestionMedicaRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorIdDebeRetornarVacioCuandoNoExiste() {
        when(gestionMedicaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<GestionMedica> resultado = gestionMedicaService.buscarPorId(99L);

        assertFalse(resultado.isPresent());
        verify(gestionMedicaRepository, times(1)).findById(99L);
    }

    @Test
    void actualizarDebeGuardarYRetornarElRegistroActualizado() {
        when(gestionMedicaRepository.save(gestionMedica)).thenReturn(gestionMedica);

        GestionMedica resultado = gestionMedicaService.actualizar(gestionMedica);

        assertEquals(gestionMedica, resultado);
        verify(gestionMedicaRepository, times(1)).save(gestionMedica);
    }

    @Test
    void buscarPorAtletaIdDebeRetornarRegistrosDelAtleta() {
        when(gestionMedicaRepository.findByAtletaId(25L)).thenReturn(List.of(gestionMedica));

        List<GestionMedica> resultado = gestionMedicaService.buscarPorAtletaId(25L);

        assertEquals(1, resultado.size());
        assertEquals(25L, resultado.get(0).getAtletaId());
        verify(gestionMedicaRepository, times(1)).findByAtletaId(25L);
    }

    @Test
    void buscarPorAtletaIdDebeRetornarListaVaciaSiElAtletaNoTieneRegistros() {
        when(gestionMedicaRepository.findByAtletaId(999L)).thenReturn(Collections.emptyList());

        List<GestionMedica> resultado = gestionMedicaService.buscarPorAtletaId(999L);

        assertTrue(resultado.isEmpty());
        verify(gestionMedicaRepository, times(1)).findByAtletaId(999L);
    }
}
