package com.polisport.entrenamiento;

import com.polisport.entrenamiento.model.Entrenamiento;
import com.polisport.entrenamiento.model.EstadoEntrenamiento;
import com.polisport.entrenamiento.model.TipoEntrenamiento;
import com.polisport.entrenamiento.repository.EntrenamientoRepository;
import com.polisport.entrenamiento.service.EntrenamientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class EntrenamientoServiceApplicationTests {

	@Autowired
	private EntrenamientoService entrenamientoService;

	@MockBean
	private EntrenamientoRepository entrenamientoRepository;

	private Entrenamiento entrenamiento;

	@BeforeEach
	void setUp() {
		entrenamiento = new Entrenamiento();
		entrenamiento.setId(1L);
		entrenamiento.setRunEntrenador(15234789);
		entrenamiento.setDvrunEntrenador("2");
		entrenamiento.setAtletasParticipantes(List.of(19876543, 20123456));
		entrenamiento.setFecha(LocalDate.of(2025, 6, 15));
		entrenamiento.setHoraInicio(LocalTime.of(8, 0));
		entrenamiento.setHoraFin(LocalTime.of(10, 0));
		entrenamiento.setTipoEntrenamiento(TipoEntrenamiento.TACTICO);
		entrenamiento.setEstado(EstadoEntrenamiento.COMPLETADO);
		entrenamiento.setDuracionMinutos(120);
		entrenamiento.setNivelIntensidad(7.5);
	}

	@Test
	void contextLoads() {
		assertNotNull(entrenamientoService);
	}

	@Test
	void shouldObtenerTodos() {
		when(entrenamientoRepository.findAll()).thenReturn(List.of(entrenamiento));
		List<Entrenamiento> result = entrenamientoService.obtenerTodos();
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}

	@Test
	void shouldObtenerPorId() {
		when(entrenamientoRepository.findById(1L)).thenReturn(Optional.of(entrenamiento));
		Optional<Entrenamiento> result = entrenamientoService.obtenerPorId(1L);
		assertTrue(result.isPresent());
		assertEquals(TipoEntrenamiento.TACTICO, result.get().getTipoEntrenamiento());
	}

	@Test
	void shouldGuardar() {
		when(entrenamientoRepository.save(any(Entrenamiento.class))).thenReturn(entrenamiento);
		Entrenamiento created = entrenamientoService.guardar(entrenamiento);
		assertNotNull(created);
		assertEquals(120, created.getDuracionMinutos());
	}

	@Test
	void shouldListarPorAtleta() {
		when(entrenamientoRepository.buscarPorAtleta(19876543)).thenReturn(List.of(entrenamiento));
		List<Entrenamiento> result = entrenamientoService.listarPorAtleta(19876543);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}
}
