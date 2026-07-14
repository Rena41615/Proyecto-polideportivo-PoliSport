package com.polisport.competencia;

import com.polisport.competencia.model.*;
import com.polisport.competencia.repository.CompetenciaRepository;
import com.polisport.competencia.service.CompetenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class CompetenciaServiceApplicationTests {

	@Autowired
	private CompetenciaService competenciaService;

	@MockBean
	private CompetenciaRepository competenciaRepository;

	private Competencia competencia;

	@BeforeEach
	void setUp() {
		competencia = new Competencia();
		competencia.setId(1L);
		competencia.setNombreCompetencia("Campeonato Nacional de Atletismo 2026");
		competencia.setLugarCompetencia("Estadio Nacional, Santiago");
		competencia.setFechaInicio(LocalDate.of(2026, 7, 15));
		competencia.setFechaFin(LocalDate.of(2026, 7, 17));
		competencia.setCategoria(Categoria.SUB18);
		competencia.setModalidad(Modalidad.INDIVIDUAL);
		competencia.setEstadoCompetencia(EstadoCompetencia.PROGRAMADA);
		competencia.setInscritosRun(List.of(20456789, 21567890));
	}

	@Test
	void contextLoads() {
		assertNotNull(competenciaService);
	}

	@Test
	void shouldObtenerTodas() {
		when(competenciaRepository.findAll()).thenReturn(List.of(competencia));
		List<Competencia> result = competenciaService.obtenerTodas();
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}

	@Test
	void shouldObtenerPorId() {
		when(competenciaRepository.findById(1L)).thenReturn(Optional.of(competencia));
		Optional<Competencia> result = competenciaService.obtenerPorId(1L);
		assertTrue(result.isPresent());
		assertEquals("Campeonato Nacional de Atletismo 2026", result.get().getNombreCompetencia());
	}

	@Test
	void shouldGuardar() {
		when(competenciaRepository.save(any(Competencia.class))).thenReturn(competencia);
		Competencia created = competenciaService.guardar(competencia);
		assertNotNull(created);
		assertEquals(Categoria.SUB18, created.getCategoria());
	}

	@Test
	void shouldListarPorAtleta() {
		when(competenciaRepository.buscarPorAtletaInscrito(20456789)).thenReturn(List.of(competencia));
		List<Competencia> result = competenciaService.listarPorAtleta(20456789);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}
}
