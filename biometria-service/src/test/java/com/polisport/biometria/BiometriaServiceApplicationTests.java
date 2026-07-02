package com.polisport.biometria;

import com.polisport.biometria.model.AnalisisBiometrico;
import com.polisport.biometria.repository.AnalisisBiometricoRepository;
import com.polisport.biometria.service.AnalisisBiometricoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class BiometriaServiceApplicationTests {

	@Autowired
	private AnalisisBiometricoService analisisBiometricoService;

	@MockBean
	private AnalisisBiometricoRepository analisisBiometricoRepository;

	private AnalisisBiometrico analisis;

	@BeforeEach
	void setUp() {
		analisis = new AnalisisBiometrico();
		analisis.setId(1L);
		analisis.setAtletaId(1L);
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
	void contextLoads() {
		assertNotNull(analisisBiometricoService);
	}

	@Test
	void shouldListAll() {
		when(analisisBiometricoRepository.findAll()).thenReturn(List.of(analisis));
		List<AnalisisBiometrico> result = analisisBiometricoService.listar();
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals(75.5, result.get(0).getPeso());
	}

	@Test
	void shouldFindById() {
		when(analisisBiometricoRepository.findById(1L)).thenReturn(Optional.of(analisis));
		Optional<AnalisisBiometrico> result = analisisBiometricoService.buscarPorId(1L);
		assertTrue(result.isPresent());
		assertEquals(22.8, result.get().getImc());
	}

	@Test
	void shouldCreate() {
		when(analisisBiometricoRepository.save(any(AnalisisBiometrico.class))).thenReturn(analisis);
		AnalisisBiometrico created = analisisBiometricoService.crear(analisis);
		assertNotNull(created);
		assertEquals(1L, created.getAtletaId());
	}

	@Test
	void shouldReturnEmptyWhenNotFound() {
		when(analisisBiometricoRepository.findById(999L)).thenReturn(Optional.empty());
		Optional<AnalisisBiometrico> result = analisisBiometricoService.buscarPorId(999L);
		assertFalse(result.isPresent());
	}
}
