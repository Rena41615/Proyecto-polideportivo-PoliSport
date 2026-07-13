package com.polisport.contratos;

import com.polisport.contratos.model.Contrato;
import com.polisport.contratos.model.EstadoContrato;
import com.polisport.contratos.model.TipoContrato;
import com.polisport.contratos.repository.ContratosRepository;
import com.polisport.contratos.service.ContratosService;
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
class ContratosServiceApplicationTests {

	@Autowired
	private ContratosService contratosService;

	@MockBean
	private ContratosRepository contratosRepository;

	private Contrato contrato;

	@BeforeEach
	void setUp() {
		contrato = new Contrato();
		contrato.setId(1L);
		contrato.setRunEmpleado(18765432);
		contrato.setDvrunEmpleado("5");
		contrato.setCargo("Entrenador");
		contrato.setFechaInicio(LocalDate.of(2025, 1, 15));
		contrato.setSalarioMensual(2500000);
		contrato.setTipoContrato(TipoContrato.PLAZO_FIJO);
		contrato.setEstado(EstadoContrato.ACTIVO);
	}

	@Test
	void contextLoads() {
		assertNotNull(contratosService);
	}

	@Test
	void shouldObtenerTodos() {
		when(contratosRepository.findAll()).thenReturn(List.of(contrato));
		List<Contrato> result = contratosService.obtenerTodos();
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
	}

	@Test
	void shouldObtenerPorId() {
		when(contratosRepository.findById(1L)).thenReturn(Optional.of(contrato));
		Optional<Contrato> result = contratosService.obtenerPorId(1L);
		assertTrue(result.isPresent());
		assertEquals("Entrenador", result.get().getCargo());
	}

	@Test
	void shouldGuardar() {
		when(contratosRepository.save(any(Contrato.class))).thenReturn(contrato);
		Contrato created = contratosService.guardar(contrato);
		assertNotNull(created);
		assertEquals(2500000, created.getSalarioMensual());
	}

	@Test
	void shouldListarPorEmpleado() {
		when(contratosRepository.findByRunEmpleado(18765432)).thenReturn(List.of(contrato));
		List<Contrato> result = contratosService.listarPorEmpleado(18765432);
		assertFalse(result.isEmpty());
		assertEquals(TipoContrato.PLAZO_FIJO, result.get(0).getTipoContrato());
	}
}
