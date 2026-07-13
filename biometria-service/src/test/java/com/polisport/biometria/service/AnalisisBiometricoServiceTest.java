package com.polisport.biometria.service;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.repository.AnalisisBiometricoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de {@link AnalisisBiometricoService} usando Mockito puro
 * (sin levantar el contexto de Spring) para verificar la interaccion con
 * {@link AnalisisBiometricoRepository} y las ramas de exito/no encontrado.
 */
@ExtendWith(MockitoExtension.class)
class AnalisisBiometricoServiceTest {

    @Mock
    private AnalisisBiometricoRepository analisisBiometricoRepository;

    @InjectMocks
    private AnalisisBiometricoService analisisBiometricoService;

    private AnalisisBiometrico analisis;

    @BeforeEach
    void setUp() {
        analisis = new AnalisisBiometrico();
        analisis.setId(1L);
        analisis.setAtletaId(5L);
        analisis.setFecha("2026-06-11");
        analisis.setPeso(75.5);
        analisis.setAltura(1.82);
        analisis.setImc(22.8);
        analisis.setPorcentajeGrasa(15.3);
        analisis.setMasaMuscular(62.4);
        analisis.setVo2Max(58.5);
        analisis.setFrecuenciaCardiacaReposo(60);
        analisis.setIndicadorRendimiento(92.5);
    }

    @Test
    void deberiaListarTodosLosAnalisisBiometricos() {
        AnalisisBiometrico otro = new AnalisisBiometrico();
        otro.setId(2L);
        otro.setAtletaId(7L);
        when(analisisBiometricoRepository.findAll()).thenReturn(Arrays.asList(analisis, otro));

        List<AnalisisBiometrico> resultado = analisisBiometricoService.listar();

        assertThat(resultado).hasSize(2).containsExactly(analisis, otro);
        verify(analisisBiometricoRepository, times(1)).findAll();
    }

    @Test
    void deberiaRetornarListaVaciaCuandoNoHayAnalisisBiometricos() {
        when(analisisBiometricoRepository.findAll()).thenReturn(Collections.emptyList());

        List<AnalisisBiometrico> resultado = analisisBiometricoService.listar();

        assertThat(resultado).isEmpty();
        verify(analisisBiometricoRepository, times(1)).findAll();
    }

    @Test
    void deberiaEncontrarAnalisisBiometricoCuandoExisteElId() {
        when(analisisBiometricoRepository.findById(1L)).thenReturn(Optional.of(analisis));

        Optional<AnalisisBiometrico> resultado = analisisBiometricoService.buscarPorId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);
        assertThat(resultado.get().getAtletaId()).isEqualTo(5L);
        verify(analisisBiometricoRepository, times(1)).findById(1L);
    }

    @Test
    void deberiaRetornarOptionalVacioCuandoNoExisteElIdBuscado() {
        when(analisisBiometricoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<AnalisisBiometrico> resultado = analisisBiometricoService.buscarPorId(99L);

        assertThat(resultado).isEmpty();
        verify(analisisBiometricoRepository, times(1)).findById(99L);
    }

    @Test
    void deberiaCrearUnNuevoAnalisisBiometrico() {
        AnalisisBiometrico sinId = new AnalisisBiometrico();
        sinId.setAtletaId(5L);
        sinId.setFecha("2026-06-11");

        when(analisisBiometricoRepository.save(sinId)).thenReturn(analisis);

        AnalisisBiometrico resultado = analisisBiometricoService.crear(sinId);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        verify(analisisBiometricoRepository, times(1)).save(sinId);
    }

    @Test
    void deberiaEliminarAnalisisBiometricoCuandoExisteElId() {
        doNothing().when(analisisBiometricoRepository).deleteById(1L);

        analisisBiometricoService.eliminar(1L);

        verify(analisisBiometricoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deberiaLanzarExcepcionAlEliminarCuandoNoExisteElId() {
        doThrow(new EmptyResultDataAccessException(1)).when(analisisBiometricoRepository).deleteById(99L);

        assertThatThrownBy(() -> analisisBiometricoService.eliminar(99L))
                .isInstanceOf(EmptyResultDataAccessException.class);

        verify(analisisBiometricoRepository, times(1)).deleteById(99L);
    }
}
